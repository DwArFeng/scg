package com.dwarfeng.scg.impl.handler.generator;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.GeneratorExecutionException;
import com.dwarfeng.scg.stack.exception.GeneratorMakeException;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.Generator.Context;
import com.dwarfeng.scg.stack.handler.Generator.ContextInfo;
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
import java.util.List;

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
    public static class GroovyGenerator extends AbstractGenerator {

        private final Processor processor;

        public GroovyGenerator(Processor processor) {
            this.processor = processor;
        }

        @Override
        public String generate(ContextInfo contextInfo) throws GeneratorException {
            try {
                return processor.generate(context, contextInfo);
            } catch (GeneratorExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new GeneratorExecutionException(e);
            }
        }

        @Override
        public List<String> generate(ContextInfo contextInfo, int size) throws GeneratorException {
            try {
                return processor.generate(context, contextInfo, size);
            } catch (GeneratorExecutionException e) {
                throw e;
            } catch (Exception e) {
                throw new GeneratorExecutionException(e);
            }
        }

        @Override
        public String toString() {
            return "GroovyGenerator{" +
                    "processor=" + processor +
                    ", context=" + context +
                    '}';
        }
    }

    /**
     * Groovy 处理器。
     *
     * @author DwArFeng
     * @since 1.0.0
     */
    public interface Processor {

        /**
         * 生成序列码。
         *
         * @param context     生成器上下文。
         * @param contextInfo 上下文信息。
         * @return 生成的序列码。
         * @throws GeneratorException 生成过程中发生的任何异常。
         */
        String generate(Context context, ContextInfo contextInfo) throws Exception;

        /**
         * 生成序列码。
         *
         * @param context     生成器上下文。
         * @param contextInfo 上下文信息。
         * @param size        生成数量。
         * @return 生成的序列码组成的列表。
         * @throws Exception 生成过程中发生的任何异常。
         */
        List<String> generate(Context context, ContextInfo contextInfo, int size) throws Exception;
    }
}
