package com.dwarfeng.scg.impl.handler.generator;

/**
 * 抽象生成器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.scg.sdk.handler.generator.AbstractGeneratorRegistry
 * @since 1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractGeneratorRegistry extends
        com.dwarfeng.scg.sdk.handler.generator.AbstractGeneratorRegistry {

    public AbstractGeneratorRegistry() {
    }

    public AbstractGeneratorRegistry(String generatorType) {
        super(generatorType);
    }
}
