package com.dwarfeng.scg.stack.exception;

/**
 * 生成器构造异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GeneratorMakeException extends GeneratorException {

    private static final long serialVersionUID = -2442155817217453087L;

    private final String GeneratorType;
    private final String param;

    public GeneratorMakeException(String GeneratorType, String param) {
        this.GeneratorType = GeneratorType;
        this.param = param;
    }

    public GeneratorMakeException(Throwable cause, String GeneratorType, String param) {
        super(cause);
        this.GeneratorType = GeneratorType;
        this.param = param;
    }

    @Override
    public String getMessage() {
        return "生成器构造异常, 类型为: " + GeneratorType + ", 参数为: " + param;
    }
}
