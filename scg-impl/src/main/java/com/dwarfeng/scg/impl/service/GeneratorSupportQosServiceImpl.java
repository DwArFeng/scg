package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.handler.GeneratorSupportHandler;
import com.dwarfeng.scg.stack.service.GeneratorSupportQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

/**
 * 生成器支持 QoS 服务实现。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Service
public class GeneratorSupportQosServiceImpl implements GeneratorSupportQosService {

    private final GeneratorSupportHandler generatorSupportHandler;

    private final ServiceExceptionMapper sem;

    public GeneratorSupportQosServiceImpl(
            GeneratorSupportHandler generatorSupportHandler,
            ServiceExceptionMapper sem
    ) {
        this.generatorSupportHandler = generatorSupportHandler;
        this.sem = sem;
    }

    @Override
    @BehaviorAnalyse
    public void reset() throws ServiceException {
        try {
            generatorSupportHandler.reset();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置生成器支持时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
