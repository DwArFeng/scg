package com.dwarfeng.scg.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 生成器异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GeneratorException extends HandlerException {

    private static final long serialVersionUID = -418757193848870823L;

    public GeneratorException() {
    }

    public GeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratorException(String message) {
        super(message);
    }

    public GeneratorException(Throwable cause) {
        super(cause);
    }
}
