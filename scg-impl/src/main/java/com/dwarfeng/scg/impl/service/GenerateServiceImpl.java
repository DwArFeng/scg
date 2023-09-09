package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.handler.GenerateHandler;
import com.dwarfeng.scg.stack.service.GenerateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenerateServiceImpl implements GenerateService {

    private final GenerateHandler generateHandler;

    private final ServiceExceptionMapper sem;

    public GenerateServiceImpl(GenerateHandler generateHandler, ServiceExceptionMapper sem) {
        this.generateHandler = generateHandler;
        this.sem = sem;
    }

    @Override
    public String generate(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.generate(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("基于指定的设置生成序列码时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public List<String> generate(StringIdKey scgSettingKey, int size) throws ServiceException {
        try {
            return generateHandler.generate(scgSettingKey, size);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("基于指定的设置生成序列码时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
