package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.sdk.util.Constants;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.handler.*;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler.GenerateContext;
import com.dwarfeng.scg.stack.handler.Generator.ContextInfo;
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String generate(StringIdKey scgSettingKey) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 确认 scgSettingKey 对应的流水码生成设置启用。
            handlerValidator.makeSureScgSettingEnabled(scgSettingKey);

            // 获取生成过程所需的参数。
            GenerateContext generateContext = generateLocalCacheHandler.get(scgSettingKey);
            ScgSetting scgSetting = generateContext.getScgSetting();
            Generator generator = generateContext.getGenerator();
            GeneratorLock lock = getGeneratorLock(scgSetting);
            ContextInfo contextInfo = new ContextInfo(scgSettingKey);

            // 调用生成器生成序列码，并返回结果。
            lock.lock();
            try {
                return generator.generate(contextInfo);
            } finally {
                lock.unlock();
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public List<String> generate(StringIdKey scgSettingKey, int size) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 确认 scgSettingKey 对应的流水码生成设置启用。
            handlerValidator.makeSureScgSettingEnabled(scgSettingKey);

            // 获取生成过程所需的参数。
            GenerateContext generateContext = generateLocalCacheHandler.get(scgSettingKey);
            ScgSetting scgSetting = generateContext.getScgSetting();
            Generator generator = generateContext.getGenerator();
            GeneratorLock lock = getGeneratorLock(scgSetting);
            ContextInfo contextInfo = new ContextInfo(scgSettingKey);

            // 调用生成器生成序列码，并返回结果。
            lock.lock();
            try {
                return generator.generate(contextInfo, size);
            } finally {
                lock.unlock();
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private GeneratorLock getGeneratorLock(ScgSetting scgSetting) throws HandlerException {
        StringIdKey scgSettingKey = scgSetting.getKey();
        int granularity = scgSetting.getGranularity();
        switch (granularity) {
            case Constants.SCG_SETTING_GRANULARITY_SETTING:
                return settingGeneratorLockLocalCacheHandler.get(scgSettingKey);
            case Constants.SCG_SETTING_GRANULARITY_DEVICE:
                return deviceGeneratorLockLocalCacheHandler.get(scgSettingKey);
            default:
                throw new IllegalStateException("未知的流水码粒度: " + granularity);
        }
    }

    @Override
    public GenerateContext getGenerateContext(StringIdKey scgSettingKey) throws HandlerException {
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
