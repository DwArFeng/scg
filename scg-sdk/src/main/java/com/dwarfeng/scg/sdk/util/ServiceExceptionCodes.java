package com.dwarfeng.scg.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 5500;

    public static final ServiceException.Code GENERATOR_FAILED =
            new ServiceException.Code(offset(0), "generator failed");
    public static final ServiceException.Code GENERATOR_EXECUTION_FAILED =
            new ServiceException.Code(offset(1), "generator execution failed");
    public static final ServiceException.Code GENERATOR_MAKE_FAILED =
            new ServiceException.Code(offset(2), "generator make failed");
    public static final ServiceException.Code UNSUPPORTED_GENERATOR_TYPE =
            new ServiceException.Code(offset(3), "unsupported generator type");
    public static final ServiceException.Code SCG_SETTING_NOT_EXISTS =
            new ServiceException.Code(offset(10), "scg setting not exists");
    public static final ServiceException.Code SCG_SETTING_DISABLED =
            new ServiceException.Code(offset(30), "scg setting disabled");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        GENERATOR_FAILED.setCode(offset(0));
        GENERATOR_EXECUTION_FAILED.setCode(offset(1));
        GENERATOR_MAKE_FAILED.setCode(offset(2));
        UNSUPPORTED_GENERATOR_TYPE.setCode(offset(3));
        SCG_SETTING_NOT_EXISTS.setCode(offset(10));
        SCG_SETTING_DISABLED.setCode(offset(30));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
