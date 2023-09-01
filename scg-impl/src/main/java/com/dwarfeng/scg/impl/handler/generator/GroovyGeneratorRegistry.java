package com.dwarfeng.scg.impl.handler.generator;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.scg.stack.bean.dto.GenerateResult;
import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.GeneratorMakeException;
import com.dwarfeng.scg.stack.handler.Generator;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy生成器注册。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class GroovyGeneratorRegistry extends AbstractGeneratorRegistry {

    public static final String GENERATOR_TYPE = "groovy_generator";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyGeneratorRegistry.class);

    private final ApplicationContext ctx;

    public GroovyGeneratorRegistry(ApplicationContext ctx) {
        super(GENERATOR_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy生成器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的Groovy脚本生成流水码。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleGeneratorProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleGeneratorProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Generator makeGenerator(String type, String param) throws GeneratorException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过Groovy脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            ctx.getAutowireCapableBeanFactory().autowireBean(processor);
            // 构建过滤器对象并返回。
            return ctx.getBean(GroovyGenerator.class, processor);
        } catch (Exception e) {
            throw new GeneratorMakeException(e, type, param);
        }
    }

    @Override
    public String toString() {
        return "GroovyGeneratorRegistry{" +
                "ctx=" + ctx +
                ", generatorType='" + generatorType + '\'' +
                '}';
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class GroovyGenerator implements Generator {

        private final Processor processor;

        public GroovyGenerator(Processor processor) {
            this.processor = processor;
        }

        @Override
        public GenerateResult generate(GenerateInfo generateInfo) throws GeneratorException {
            return processor.generate(generateInfo);
        }

        @Override
        public String toString() {
            return "GroovyGenerator{" +
                    "processor=" + processor +
                    '}';
        }
    }

    /**
     * Groovy处理器。
     *
     * @author DwArFeng
     * @since 1.0.0
     */
    public interface Processor {

        /**
         * 生成串码及关联信息。
         *
         * @param generateInfo 与生成串码相关的信息。
         * @return 生成结果，包含串码本身和其它相关信息。
         * @throws GeneratorException 生成器异常。
         */
        GenerateResult generate(GenerateInfo generateInfo) throws GeneratorException;
    }
}
