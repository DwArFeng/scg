package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.sdk.handler.GeneratorSupporter;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.handler.GeneratorSupportHandler;
import com.dwarfeng.scg.stack.service.GeneratorSupportMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 生成器支持处理器实现。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Component
public class GeneratorSupportHandlerImpl implements GeneratorSupportHandler {

    private final GeneratorSupportMaintainService generatorSupportMaintainService;

    private final List<GeneratorSupporter> generatorSupporters;

    public GeneratorSupportHandlerImpl(
            GeneratorSupportMaintainService generatorSupportMaintainService,
            List<GeneratorSupporter> generatorSupporters
    ) {
        this.generatorSupportMaintainService = generatorSupportMaintainService;
        this.generatorSupporters = Optional.ofNullable(generatorSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void reset() throws HandlerException {
        try {
            doReset();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doReset() throws Exception {
        List<StringIdKey> generatorKeys = generatorSupportMaintainService.lookupAsList().stream()
                .map(GeneratorSupport::getKey).collect(Collectors.toList());
        generatorSupportMaintainService.batchDelete(generatorKeys);
        List<GeneratorSupport> generatorSupports = generatorSupporters.stream().map(
                supporter -> new GeneratorSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        generatorSupportMaintainService.batchInsert(generatorSupports);
    }
}
