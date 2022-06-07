package com.dwarfeng.scg.impl.handler.generator;

import com.dwarfeng.scg.impl.handler.GeneratorMaker;
import com.dwarfeng.scg.impl.handler.GeneratorSupporter;

import java.util.Objects;

/**
 * 抽象生成器注册。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public abstract class AbstractGeneratorRegistry implements GeneratorMaker, GeneratorSupporter {

    protected String generatorType;

    public AbstractGeneratorRegistry() {
    }

    public AbstractGeneratorRegistry(String generatorType) {
        this.generatorType = generatorType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(generatorType, type);
    }

    @Override
    public String provideType() {
        return generatorType;
    }

    public String getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(String generatorType) {
        this.generatorType = generatorType;
    }

    @Override
    public String toString() {
        return "AbstractGeneratorRegistry{" +
                "generatorType='" + generatorType + '\'' +
                '}';
    }
}
