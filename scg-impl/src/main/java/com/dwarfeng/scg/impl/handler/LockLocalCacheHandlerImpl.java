package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.LockLocalCacheHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
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
public class LockLocalCacheHandlerImpl implements LockLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, Lock> handler;

    public LockLocalCacheHandlerImpl(LockFetcher lockFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(lockFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Lock get(StringIdKey key) throws HandlerException {
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
    public static class LockFetcher implements Fetcher<StringIdKey, Lock> {

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
        public Lock fetch(StringIdKey key) {
            return new ReentrantLock();
        }
    }
}
