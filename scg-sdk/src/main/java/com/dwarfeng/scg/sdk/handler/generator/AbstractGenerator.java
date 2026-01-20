package com.dwarfeng.scg.sdk.handler.generator;

import com.dwarfeng.scg.stack.handler.Generator;

/**
 * 抽象生成器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public abstract class AbstractGenerator implements Generator {

    protected Context context;

    public AbstractGenerator() {
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "AbstractGenerator{" +
                "context=" + context +
                '}';
    }
}
