package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.SettingGeneratorLockLocalCacheHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SettingGeneratorLockLocalCacheHandlerImpl implements SettingGeneratorLockLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, GeneratorLock> handler;

    public SettingGeneratorLockLocalCacheHandlerImpl(LockFetcher lockFetcher) {
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
    public void clear() {
        handler.clear();
    }

    @Component
    public static class LockFetcher implements Fetcher<StringIdKey, GeneratorLock> {

        private final ScgSettingMaintainService scgSettingMaintainService;

        private final CuratorFramework curatorFramework;

        @Value("${curator.mutex_prefix.setting_generator_lock}")
        private String mutexPrefix;

        public LockFetcher(
                ScgSettingMaintainService scgSettingMaintainService,
                CuratorFramework curatorFramework
        ) {
            this.scgSettingMaintainService = scgSettingMaintainService;
            this.curatorFramework = curatorFramework;
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
            InterProcessMutex interProcessMutex = new InterProcessMutex(
                    curatorFramework, mutexPrefix + key.getStringId()
            );
            return new SettingGeneratorLock(interProcessMutex);
        }
    }

    private static class SettingGeneratorLock implements GeneratorLock {

        private final InterProcessMutex interProcessMutex;

        public SettingGeneratorLock(InterProcessMutex interProcessMutex) {
            this.interProcessMutex = interProcessMutex;
        }

        @Override
        public void lock() throws Exception {
            interProcessMutex.acquire();
        }

        @Override
        public void unlock() throws Exception {
            interProcessMutex.release();
        }

        @Override
        public String toString() {
            return "SettingGeneratorLock{" +
                    "interProcessMutex=" + interProcessMutex +
                    '}';
        }
    }
}
