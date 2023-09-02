package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.handler.GenerateHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<String> generate(StringIdKey scgSettingKey, int size) throws ServiceException {
        try {
            return generateHandler.generate(scgSettingKey, size);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("基于指定的设置生成序列码时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public Generator getGenerator(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.getGenerator(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取指定主键对应的生成器时发生异常", LogLevel.WARN, sem, e);
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
    public GeneratorLock getSettingGeneratorLock(StringIdKey scgSettingKey) throws ServiceException {
        try {
            return generateHandler.getSettingGeneratorLock(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow(
                    "获取指定流水码生成设置主键对应的设置生成器锁时发生异常", LogLevel.WARN, sem, e
            );
        }
    }

    @Override
    public void clearSettingGeneratorLockLocalCache() throws ServiceException {
        try {
            generateHandler.clearSettingGeneratorLockLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除设置生成器锁本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public GeneratorLock getDeviceGeneratorLock(StringIdKey scgDeviceKey) throws ServiceException {
        try {
            return generateHandler.getDeviceGeneratorLock(scgDeviceKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow(
                    "获取指定流水码生成设备主键对应的设备生成器锁时发生异常", LogLevel.WARN, sem, e
            );
        }
    }

    @Override
    public void clearDeviceGeneratorLockLocalCache() throws ServiceException {
        try {
            generateHandler.clearDeviceGeneratorLockLocalCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除设备生成器锁本地缓存时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
