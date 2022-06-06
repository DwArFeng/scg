package com.dwarfeng.scg.stack.exception;

/**
 * 不支持的生成器类型异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedGeneratorTypeException extends GeneratorException {

    private static final long serialVersionUID = -3199183291338399363L;

    private final String type;

    public UnsupportedGeneratorTypeException(String type) {
        this.type = type;
    }

    public UnsupportedGeneratorTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的生成器类型: " + type;
    }
}
