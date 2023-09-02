package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.handler.DeviceGeneratorLockLocalCacheHandler;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler;
import com.dwarfeng.scg.stack.handler.PushHandler;
import com.dwarfeng.scg.stack.handler.SettingGeneratorLockLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final GenerateLocalCacheHandler generateLocalCacheHandler;
    private final SettingGeneratorLockLocalCacheHandler settingGeneratorLockLocalCacheHandler;
    private final DeviceGeneratorLockLocalCacheHandler deviceGeneratorLockLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            GenerateLocalCacheHandler generateLocalCacheHandler,
            SettingGeneratorLockLocalCacheHandler settingGeneratorLockLocalCacheHandler,
            DeviceGeneratorLockLocalCacheHandler deviceGeneratorLockLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.generateLocalCacheHandler = generateLocalCacheHandler;
        this.settingGeneratorLockLocalCacheHandler = settingGeneratorLockLocalCacheHandler;
        this.deviceGeneratorLockLocalCacheHandler = deviceGeneratorLockLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetGenerate() throws HandlerException {
        generateLocalCacheHandler.clear();
        settingGeneratorLockLocalCacheHandler.clear();
        deviceGeneratorLockLocalCacheHandler.clear();

        try {
            pushHandler.generateReset();
        } catch (Exception e) {
            LOGGER.warn("推送生成功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
