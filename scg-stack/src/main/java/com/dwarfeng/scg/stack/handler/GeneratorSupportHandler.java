package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 生成器支持处理器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface GeneratorSupportHandler extends Handler {

    /**
     * 重置生成器支持。
     *
     * @throws HandlerException 处理器异常。
     */
    void reset() throws HandlerException;
}
