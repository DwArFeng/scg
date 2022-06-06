package com.dwarfeng.scg.stack.exception;

/**
 * 生成器执行异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GeneratorExecutionException extends GeneratorException {

    private static final long serialVersionUID = 5433839841694277564L;

    public GeneratorExecutionException() {
    }

    public GeneratorExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneratorExecutionException(String message) {
        super(message);
    }

    public GeneratorExecutionException(Throwable cause) {
        super(cause);
    }
}
