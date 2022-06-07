package com.dwarfeng.scg.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 流水码生成设置设置不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ScgSettingNotExistsException extends HandlerException {

    private static final long serialVersionUID = 7084513616876510418L;

    private final StringIdKey scgSettingKey;

    public ScgSettingNotExistsException(StringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    public ScgSettingNotExistsException(Throwable cause, StringIdKey scgSettingKey) {
        super(cause);
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public String getMessage() {
        return "流水码生成设置设置 " + scgSettingKey + " 不存在";
    }
}
