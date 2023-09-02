package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface Pusher {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 生成功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void generateReset() throws HandlerException;
}
