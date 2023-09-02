package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.DeviceGeneratorLockLocalCacheHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DeviceGeneratorLockLocalCacheHandlerImpl implements DeviceGeneratorLockLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, GeneratorLock> handler;

    public DeviceGeneratorLockLocalCacheHandlerImpl(LockFetcher lockFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(lockFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public GeneratorLock get(StringIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(StringIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() throws HandlerException {
        handler.clear();
    }

    @Component
    public static class LockFetcher implements Fetcher<StringIdKey, GeneratorLock> {

        private final ScgSettingMaintainService scgSettingMaintainService;

        public LockFetcher(ScgSettingMaintainService scgSettingMaintainService) {
            this.scgSettingMaintainService = scgSettingMaintainService;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(StringIdKey key) throws Exception {
            return scgSettingMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public GeneratorLock fetch(StringIdKey key) {
            return new DeviceGeneratorLock(new ReentrantLock());
        }
    }

    private static class DeviceGeneratorLock implements GeneratorLock {

        private final Lock lock;

        public DeviceGeneratorLock(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void lock() {
            lock.lock();
        }

        @Override
        public void unlock() {
            lock.unlock();
        }

        @Override
        public String toString() {
            return "DeviceGeneratorLock{" +
                    "lock=" + lock +
                    '}';
        }
    }
}
