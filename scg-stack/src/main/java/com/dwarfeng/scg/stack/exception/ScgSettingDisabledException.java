package com.dwarfeng.scg.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 流水码生成设置设置被禁用异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class ScgSettingDisabledException extends HandlerException {

    private static final long serialVersionUID = 1428098295662877440L;

    private final StringIdKey scgSettingKey;

    public ScgSettingDisabledException(StringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    public ScgSettingDisabledException(Throwable cause, StringIdKey scgSettingKey) {
        super(cause);
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public String getMessage() {
        return "流水码生成设置设置 " + scgSettingKey + " 被禁用";
    }
}
