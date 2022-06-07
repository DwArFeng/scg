package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.exception.GeneratorException;

/**
 * 生成器处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GeneratorHandler {

    /**
     * 根据指定的判断器信息构造一个判断器。
     *
     * @param type  生成器类型。
     * @param param 生成器参数。
     * @return 构造的判断器。
     * @throws GeneratorException 生成器异常。
     */
    Generator make(String type, String param) throws GeneratorException;
}
