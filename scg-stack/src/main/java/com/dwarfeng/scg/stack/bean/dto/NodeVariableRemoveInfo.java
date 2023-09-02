package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 节点变量移除信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NodeVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = -6893360829909628145L;

    private String scgSettingId;
    private Integer deviceId;
    private String variableId;

    public NodeVariableRemoveInfo() {
    }

    public NodeVariableRemoveInfo(String scgSettingId, Integer deviceId, String variableId) {
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
    public String toString() {
        return "NodeVariableRemoveInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
