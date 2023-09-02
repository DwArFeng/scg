package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.*;
import com.dwarfeng.scg.stack.handler.Generator.SerialCodeGranularity;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenerateHandlerImpl implements GenerateHandler {

    private final GenerateLocalCacheHandler generateLocalCacheHandler;
    private final SettingGeneratorLockLocalCacheHandler settingGeneratorLockLocalCacheHandler;
    private final DeviceGeneratorLockLocalCacheHandler deviceGeneratorLockLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    public GenerateHandlerImpl(
            GenerateLocalCacheHandler generateLocalCacheHandler,
            SettingGeneratorLockLocalCacheHandler settingGeneratorLockLocalCacheHandler,
            DeviceGeneratorLockLocalCacheHandler deviceGeneratorLockLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.generateLocalCacheHandler = generateLocalCacheHandler;
        this.settingGeneratorLockLocalCacheHandler = settingGeneratorLockLocalCacheHandler;
        this.deviceGeneratorLockLocalCacheHandler = deviceGeneratorLockLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public String generate(StringIdKey scgSettingKey) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 确认 scgSettingKey 对应的流水码生成设置启用。
            handlerValidator.makeSureScgSettingEnabled(scgSettingKey);

            // 获取 scgSettingKey 对应的生成器。
            Generator generator = generateLocalCacheHandler.get(scgSettingKey);

            // 获取生成器需要的锁。
            GeneratorLock lock = getGeneratorLock(scgSettingKey, generator);

            // 调用生成器生成序列码，并返回结果。
            lock.lock();
            try {
                return generator.generate();
            } finally {
                lock.unlock();
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public List<String> generate(StringIdKey scgSettingKey, int size) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 确认 scgSettingKey 对应的流水码生成设置启用。
            handlerValidator.makeSureScgSettingEnabled(scgSettingKey);

            // 获取 scgSettingKey 对应的生成器。
            Generator generator = generateLocalCacheHandler.get(scgSettingKey);

            // 获取生成器需要的锁。
            GeneratorLock lock = getGeneratorLock(scgSettingKey, generator);

            // 调用生成器生成序列码，并返回结果。
            lock.lock();
            try {
                return generator.generate(size);
            } finally {
                lock.unlock();
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private GeneratorLock getGeneratorLock(StringIdKey scgSettingKey, Generator generator) throws HandlerException {
        SerialCodeGranularity serialCodeGranularity = generator.getSerialCodeGranularity();
        switch (serialCodeGranularity) {
            case SETTING:
                return settingGeneratorLockLocalCacheHandler.get(scgSettingKey);
            case DEVICE:
                return deviceGeneratorLockLocalCacheHandler.get(scgSettingKey);
            default:
                throw new IllegalStateException("未知的流水码粒度: " + serialCodeGranularity);
        }
    }

    @Override
    public Generator getGenerator(StringIdKey scgSettingKey) throws HandlerException {
        return generateLocalCacheHandler.get(scgSettingKey);
    }

    @Override
    public void clearGenerateLocalCache() throws HandlerException {
        generateLocalCacheHandler.clear();
    }

    @Override
    public GeneratorLock getSettingGeneratorLock(StringIdKey scgSettingKey) throws HandlerException {
        return settingGeneratorLockLocalCacheHandler.get(scgSettingKey);
    }

    @Override
    public void clearSettingGeneratorLockLocalCache() throws HandlerException {
        settingGeneratorLockLocalCacheHandler.clear();
    }

    @Override
    public GeneratorLock getDeviceGeneratorLock(StringIdKey scgDeviceKey) throws HandlerException {
        return deviceGeneratorLockLocalCacheHandler.get(scgDeviceKey);
    }

    @Override
    public void clearDeviceGeneratorLockLocalCache() throws HandlerException {
        deviceGeneratorLockLocalCacheHandler.clear();
    }

    @Override
    public String toString() {
        return "GenerateHandlerImpl{" +
                "generateLocalCacheHandler=" + generateLocalCacheHandler +
                ", settingGeneratorLockLocalCacheHandler=" + settingGeneratorLockLocalCacheHandler +
                ", deviceGeneratorLockLocalCacheHandler=" + deviceGeneratorLockLocalCacheHandler +
                ", handlerValidator=" + handlerValidator +
                '}';
    }
}
