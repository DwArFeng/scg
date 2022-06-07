package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.handler.GenerateHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;

@Service
public class GenerateQosServiceImpl implements GenerateQosService {

    private final GenerateHandler generateHandler;

    private final ServiceExceptionMapper sem;

    public GenerateQosServiceImpl(GenerateHandler generateHandler, ServiceExceptionMapper sem) {
        this.generateHandler = generateHandler;
        this.sem = sem;
    }

    @Override
    public String generate(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.generate(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("基于指定的设置生成序列码时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public List<String> batchGenerate(StringIdKey scgSettingKey, int size) throws ServiceException {
        try {
            return generateHandler.batchGenerate(scgSettingKey, size);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("基于指定的设置批量生成序列码时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Generator getGenerator(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.getGenerator(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的本地锁时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void clearGenerateLocalCache() throws ServiceException {
        try {
            generateHandler.clearGenerateLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除生成器本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Lock getLocalLock(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.getLocalLock(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的本地锁时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void clearLockLocalCache() throws ServiceException {
        try {
            generateHandler.clearLockLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除锁本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
