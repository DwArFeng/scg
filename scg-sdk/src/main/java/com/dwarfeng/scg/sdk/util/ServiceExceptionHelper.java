package com.dwarfeng.scg.sdk.util;

import com.dwarfeng.scg.stack.exception.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常的帮助工具类。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 scg 默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速的搭建目标映射。
     *
     * @param map 指定的映射，允许为 <code>null</code>。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, ServiceException.Code> putDefaultDestination(
            Map<Class<? extends Exception>, ServiceException.Code> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(GeneratorException.class, ServiceExceptionCodes.GENERATOR_FAILED);
        map.put(GeneratorExecutionException.class, ServiceExceptionCodes.GENERATOR_EXECUTION_FAILED);
        map.put(GeneratorMakeException.class, ServiceExceptionCodes.GENERATOR_MAKE_FAILED);
        map.put(UnsupportedGeneratorTypeException.class, ServiceExceptionCodes.UNSUPPORTED_GENERATOR_TYPE);
        map.put(ScgSettingNotExistsException.class, ServiceExceptionCodes.SCG_SETTING_NOT_EXISTS);
        map.put(ScgSettingDisabledException.class, ServiceExceptionCodes.SCG_SETTING_DISABLED);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}
