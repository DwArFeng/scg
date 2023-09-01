package com.dwarfeng.scg.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 节点变量主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class HibernateNodeVariableKey implements Key {

    private static final long serialVersionUID = 4129355521021583383L;

    private String scgSettingId;
    private Integer deviceId;
    private String variableId;

    public HibernateNodeVariableKey() {
    }

    public HibernateNodeVariableKey(String scgSettingId, Integer deviceId, String variableId) {
        this.scgSettingId = scgSettingId;
        this.deviceId = deviceId;
        this.variableId = variableId;
    }

    public String getScgSettingId() {
        return scgSettingId;
    }

    public void setScgSettingId(String scgSettingId) {
        this.scgSettingId = scgSettingId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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

        HibernateNodeVariableKey that = (HibernateNodeVariableKey) o;

        if (!Objects.equals(scgSettingId, that.scgSettingId)) return false;
        if (!Objects.equals(deviceId, that.deviceId)) return false;
        return Objects.equals(variableId, that.variableId);
    }

    @Override
    public int hashCode() {
        int result = scgSettingId != null ? scgSettingId.hashCode() : 0;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (variableId != null ? variableId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateNodeVariableKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
