package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.UnsupportedGeneratorTypeException;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.GeneratorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class GeneratorHandlerImpl implements GeneratorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorHandlerImpl.class);

    private final List<GeneratorMaker> generatorMakers;

    public GeneratorHandlerImpl(List<GeneratorMaker> generatorMakers) {
        this.generatorMakers = Optional.ofNullable(generatorMakers).orElse(Collections.emptyList());
    }

    @Override
    public Generator make(String type, String param) throws GeneratorException {
        try {
            // 生成生成器。
            LOGGER.debug("通过生成器信息构建新的的生成器...");
            GeneratorMaker generatorMaker = generatorMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedGeneratorTypeException(type));
            Generator generator = generatorMaker.makeGenerator(type, param);
            LOGGER.debug("生成器构建成功!");
            LOGGER.debug("生成器: " + generator);
            return generator;
        } catch (GeneratorException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }
}
