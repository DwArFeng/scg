package com.dwarfeng.scg.node.launcher;

import com.dwarfeng.scg.node.handler.LauncherSettingHandler;
import com.dwarfeng.scg.stack.service.GeneratorSupportMaintainService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);
            // 判断是否重置生成器支持。
            if (launcherSettingHandler.isResetGeneratorSupport()) {
                LOGGER.info("重置生成器支持...");
                GeneratorSupportMaintainService maintainService = ctx.getBean(GeneratorSupportMaintainService.class);
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("生成器支持重置失败，异常信息如下", e);
                }
            }
        });
    }
}
