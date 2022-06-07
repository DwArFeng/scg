package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.LockLocalCacheHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class LockLocalCacheHandlerImpl implements LockLocalCacheHandler {

    private final LockFetcher lockFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<StringIdKey, Lock> lockMap = new HashMap<>();
    private final Set<StringIdKey> notExistSettings = new HashSet<>();

    public LockLocalCacheHandlerImpl(LockFetcher lockFetcher) {
        this.lockFetcher = lockFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Lock getLocalLock(StringIdKey scgSettingKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (lockMap.containsKey(scgSettingKey)) {
                    return lockMap.get(scgSettingKey);
                }
                if (notExistSettings.contains(scgSettingKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (lockMap.containsKey(scgSettingKey)) {
                    return lockMap.get(scgSettingKey);
                }
                if (notExistSettings.contains(scgSettingKey)) {
                    return null;
                }
                Lock lock = lockFetcher.fetchLock(scgSettingKey);
                if (Objects.nonNull(lock)) {
                    lockMap.put(scgSettingKey, lock);
                    return lock;
                }
                notExistSettings.add(scgSettingKey);
                return null;
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            lockMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class LockFetcher {

        private final ScgSettingMaintainService scgSettingMaintainService;

        public LockFetcher(ScgSettingMaintainService scgSettingMaintainService) {
            this.scgSettingMaintainService = scgSettingMaintainService;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Lock fetchLock(StringIdKey scgSettingKey) throws Exception {
            if (!scgSettingMaintainService.exists(scgSettingKey)) {
                return null;
            }
            return new ReentrantLock();
        }
    }
}
