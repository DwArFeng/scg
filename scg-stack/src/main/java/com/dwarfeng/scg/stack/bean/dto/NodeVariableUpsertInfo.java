package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;

/**
 * 节点变量插入更新信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NodeVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = 4712125160947933476L;

    private String scgSettingId;
    private Integer deviceId;
    private String variableId;
    private String stringValue;
    private Boolean booleanValue;
    private Integer integerValue;
    private Long longValue;
    private Double doubleValue;
    private Date dateValue;

    public NodeVariableUpsertInfo() {
    }

    public NodeVariableUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, String stringValue, Boolean booleanValue,
            Integer integerValue, Long longValue, Double doubleValue, Date dateValue
    ) {
        this.scgSettingId = scgSettingId;
        this.deviceId = deviceId;
        this.variableId = variableId;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.integerValue = integerValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.dateValue = dateValue;
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

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    @Override
    public String toString() {
        return "NodeVariableUpsertInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                ", stringValue='" + stringValue + '\'' +
                ", booleanValue=" + booleanValue +
                ", integerValue=" + integerValue +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", dateValue=" + dateValue +
                '}';
    }
}
