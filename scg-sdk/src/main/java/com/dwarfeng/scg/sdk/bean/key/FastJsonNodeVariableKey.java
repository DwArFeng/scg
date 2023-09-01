package com.dwarfeng.scg.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 节点变量主键。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class FastJsonNodeVariableKey implements Key {

    private static final long serialVersionUID = -1807955891371530359L;

    public static FastJsonNodeVariableKey of(NodeVariableKey nodeVariableKey) {
        if (Objects.isNull(nodeVariableKey)) {
            return null;
        } else {
            return new FastJsonNodeVariableKey(
                    nodeVariableKey.getScgSettingId(),
                    nodeVariableKey.getDeviceId(),
                    nodeVariableKey.getVariableId()
            );
        }
    }

    @JSONField(name = "scg_setting_id", ordinal = 1)
    private String scgSettingId;

    @JSONField(name = "device_id", ordinal = 2)
    private Integer deviceId;

    @JSONField(name = "variable_id", ordinal = 3)
    private String variableId;

    public FastJsonNodeVariableKey() {
    }

    public FastJsonNodeVariableKey(String scgSettingId, Integer deviceId, String variableId) {
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

        FastJsonNodeVariableKey that = (FastJsonNodeVariableKey) o;

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
        return "FastJsonNodeVariableKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
