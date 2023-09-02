package com.dwarfeng.scg.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_generator_support}")
    private boolean resetGeneratorSupport;

    @Value("${launcher.start_reset_delay}")
    private long startResetDelay;

    public boolean isResetGeneratorSupport() {
        return resetGeneratorSupport;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetGeneratorSupport=" + resetGeneratorSupport +
                ", startResetDelay=" + startResetDelay +
                '}';
    }
}
