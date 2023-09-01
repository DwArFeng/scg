package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.GeneratorHandler;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GenerateLocalCacheHandlerImpl implements GenerateLocalCacheHandler {

    private final GeneralLocalCacheHandler<StringIdKey, Generator> handler;

    public GenerateLocalCacheHandlerImpl(GeneratorFetcher generatorFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(generatorFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(StringIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Generator get(StringIdKey key) throws HandlerException {
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
    public static class GeneratorFetcher implements Fetcher<StringIdKey, Generator> {

        private final ScgSettingMaintainService scgSettingMaintainService;

        private final GeneratorHandler generatorHandler;

        public GeneratorFetcher(
                ScgSettingMaintainService scgSettingMaintainService, GeneratorHandler generatorHandler
        ) {
            this.scgSettingMaintainService = scgSettingMaintainService;
            this.generatorHandler = generatorHandler;
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
        public Generator fetch(StringIdKey key) throws Exception {
            ScgSetting scgSetting = scgSettingMaintainService.get(key);
            return generatorHandler.make(scgSetting.getType(), scgSetting.getParam());
        }
    }
}
