package com.dwarfeng.scg.sdk.handler;

import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.handler.Generator;

/**
 * 生成器构造器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface GeneratorMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的生成器信息生成一个生成器对象。
     *
     * <p>
     * 可以保证传入的生成器信息中的类型是支持的。
     *
     * @param type  生成器类型。
     * @param param 生成器参数。
     * @return 制造出的生成器。
     * @throws GeneratorException 生成器异常。
     */
    Generator makeGenerator(String type, String param) throws GeneratorException;
}
