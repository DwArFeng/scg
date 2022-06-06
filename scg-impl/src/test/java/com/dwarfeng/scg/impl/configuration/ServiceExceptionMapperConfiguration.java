package com.dwarfeng.scg.impl.configuration;

import com.dwarfeng.scg.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.GeneratorExecutionException;
import com.dwarfeng.scg.stack.exception.GeneratorMakeException;
import com.dwarfeng.scg.stack.exception.UnsupportedGeneratorTypeException;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(GeneratorException.class, ServiceExceptionCodes.GENERATOR_FAILED);
        destination.put(GeneratorExecutionException.class, ServiceExceptionCodes.GENERATOR_EXECUTION_FAILED);
        destination.put(GeneratorMakeException.class, ServiceExceptionCodes.GENERATOR_MAKE_FAILED);
        destination.put(UnsupportedGeneratorTypeException.class, ServiceExceptionCodes.UNSUPPORTED_GENERATOR_TYPE);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
