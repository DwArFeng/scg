package com.dwarfeng.scg.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_generator_support}")
    private boolean resetGeneratorSupport;

    public boolean isResetGeneratorSupport() {
        return resetGeneratorSupport;
    }
}
