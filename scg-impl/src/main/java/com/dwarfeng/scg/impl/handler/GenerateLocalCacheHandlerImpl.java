package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.GeneratorHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class GenerateLocalCacheHandlerImpl implements GenerateLocalCacheHandler {

    private final GeneratorFetcher generatorFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<StringIdKey, Generator> generatorMap = new HashMap<>();
    private final Set<StringIdKey> notExistSettings = new HashSet<>();

    public GenerateLocalCacheHandlerImpl(GeneratorFetcher generatorFetcher) {
        this.generatorFetcher = generatorFetcher;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public Generator getGenerator(StringIdKey scgSettingKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (generatorMap.containsKey(scgSettingKey)) {
                    return generatorMap.get(scgSettingKey);
                }
                if (notExistSettings.contains(scgSettingKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (generatorMap.containsKey(scgSettingKey)) {
                    return generatorMap.get(scgSettingKey);
                }
                if (notExistSettings.contains(scgSettingKey)) {
                    return null;
                }
                Generator generator = generatorFetcher.fetchGenerator(scgSettingKey);
                if (Objects.nonNull(generator)) {
                    generatorMap.put(scgSettingKey, generator);
                    return generator;
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
            generatorMap.clear();
            notExistSettings.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class GeneratorFetcher {

        private final ScgSettingMaintainService scgSettingMaintainService;

        private final GeneratorHandler generatorHandler;

        public GeneratorFetcher(
                ScgSettingMaintainService scgSettingMaintainService, GeneratorHandler generatorHandler
        ) {
            this.scgSettingMaintainService = scgSettingMaintainService;
            this.generatorHandler = generatorHandler;
        }

        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Generator fetchGenerator(StringIdKey scgSettingKey) throws Exception {
            if (!scgSettingMaintainService.exists(scgSettingKey)) {
                return null;
            }
            ScgSetting scgSetting = scgSettingMaintainService.get(scgSettingKey);
            return generatorHandler.make(scgSetting.getType(), scgSetting.getParam());
        }
    }
}
