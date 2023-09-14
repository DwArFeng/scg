package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 生成信息。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public class GenerateInfo implements Dto {

    private static final long serialVersionUID = 7673581602526374376L;
    
    private StringIdKey scgSettingKey;

    public GenerateInfo() {
    }

    public GenerateInfo(StringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    public StringIdKey getScgSettingKey() {
        return scgSettingKey;
    }

    public void setScgSettingKey(StringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public String toString() {
        return "GenerateInfo{" +
                "scgSettingKey=" + scgSettingKey +
                '}';
    }
}
