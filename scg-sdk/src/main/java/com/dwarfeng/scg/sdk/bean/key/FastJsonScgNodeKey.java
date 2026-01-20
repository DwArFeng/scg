package com.dwarfeng.scg.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 流水码生成节点主键。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonScgNodeKey implements Key {

    private static final long serialVersionUID = 7396790012931442415L;

    public static FastJsonScgNodeKey of(ScgNodeKey scgNodeKey) {
        if (Objects.isNull(scgNodeKey)) {
            return null;
        } else {
            return new FastJsonScgNodeKey(
                    scgNodeKey.getScgSettingId(), scgNodeKey.getDeviceId()
            );
        }
    }

    @JSONField(name = "scg_setting_id", ordinal = 1)
    private String scgSettingId;

    @JSONField(name = "device_id", ordinal = 2)
    private Integer deviceId;

    public FastJsonScgNodeKey() {
    }

    public FastJsonScgNodeKey(String scgSettingId, Integer deviceId) {
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

        FastJsonScgNodeKey that = (FastJsonScgNodeKey) o;

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
        return "FastJsonScgNodeKey{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                '}';
    }
}
