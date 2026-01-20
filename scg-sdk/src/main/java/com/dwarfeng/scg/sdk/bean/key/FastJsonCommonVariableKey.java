package com.dwarfeng.scg.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 公共变量主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonCommonVariableKey implements Key {

    private static final long serialVersionUID = -1482609591808914369L;

    public static FastJsonCommonVariableKey of(CommonVariableKey commonVariableKey) {
        if (Objects.isNull(commonVariableKey)) {
            return null;
        } else {
            return new FastJsonCommonVariableKey(
                    commonVariableKey.getScgSettingId(),
                    commonVariableKey.getVariableId()
            );
        }
    }

    @JSONField(name = "scg_setting_id", ordinal = 1)
    private String scgSettingId;

    @JSONField(name = "variable_id", ordinal = 2)
    private String variableId;

    public FastJsonCommonVariableKey() {
    }

    public FastJsonCommonVariableKey(String scgSettingId, String variableId) {
        this.scgSettingId = scgSettingId;
        this.variableId = variableId;
    }

    public String getScgSettingId() {
        return scgSettingId;
    }

    public void setScgSettingId(String scgSettingId) {
        this.scgSettingId = scgSettingId;
    }

    public String getVariableId() {
        return variableId;
    }

    public void setVariableId(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonCommonVariableKey that = (FastJsonCommonVariableKey) o;

        if (!Objects.equals(scgSettingId, that.scgSettingId)) return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = scgSettingId != null ? scgSettingId.hashCode() : 0;
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonCommonVariableKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
