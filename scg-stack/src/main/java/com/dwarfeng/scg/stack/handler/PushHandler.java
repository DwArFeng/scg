package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送器处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface PushHandler extends Handler {

    /**
     * 生成功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void generateReset() throws HandlerException;
}
