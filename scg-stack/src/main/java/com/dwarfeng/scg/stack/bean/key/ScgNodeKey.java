package com.dwarfeng.scg.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 流水码生成节点主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ScgNodeKey implements Key {

    private static final long serialVersionUID = -5259367624391111209L;

    private String scgSettingId;
    private Integer deviceId;

    public ScgNodeKey() {
    }

    public ScgNodeKey(String scgSettingId, Integer deviceId) {
        this.scgSettingId = scgSettingId;
        this.deviceId = deviceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScgNodeKey that = (ScgNodeKey) o;

        if (!Objects.equals(scgSettingId, that.scgSettingId)) return false;
        return Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        int result = scgSettingId != null ? scgSettingId.hashCode() : 0;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ScgNodeKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                '}';
    }
}
