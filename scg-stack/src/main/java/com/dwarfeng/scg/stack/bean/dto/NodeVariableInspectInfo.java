package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 节点变量查看信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NodeVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -3503692175417428349L;

    private String scgSettingId;
    private Integer deviceId;
    private String variableId;

    public NodeVariableInspectInfo() {
    }

    public NodeVariableInspectInfo(String scgSettingId, Integer deviceId, String variableId) {
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
        return "NodeVariableInspectInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
