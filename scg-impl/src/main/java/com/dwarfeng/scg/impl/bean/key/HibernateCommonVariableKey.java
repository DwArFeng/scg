package com.dwarfeng.scg.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 公共变量主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateCommonVariableKey implements Key {

    private static final long serialVersionUID = 7705635784249849289L;

    private String scgSettingId;
    private String variableId;

    public HibernateCommonVariableKey() {
    }

    public HibernateCommonVariableKey(String scgSettingId, String variableId) {
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

        HibernateCommonVariableKey that = (HibernateCommonVariableKey) o;

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
        return "HibernateCommonVariableKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
