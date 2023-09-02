package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 节点变量插入更新信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputNodeVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = 3563710191092780716L;

    public static NodeVariableUpsertInfo toStackBean(WebInputNodeVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NodeVariableUpsertInfo(
                    webInput.getScgSettingId(),
                    webInput.getDeviceId(),
                    webInput.getVariableId(),
                    webInput.getStringValue(),
                    webInput.getBooleanValue(),
                    webInput.getIntegerValue(),
                    webInput.getLongValue(),
                    webInput.getDoubleValue(),
                    webInput.getDateValue()
            );
        }
    }

    @JSONField(name = "scg_setting_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String scgSettingId;

    @JSONField(name = "device_id")
    @NotNull
    private Integer deviceId;

    @JSONField(name = "variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_ID)
    private String variableId;

    @JSONField(name = "string_value")
    private String stringValue;

    @JSONField(name = "boolean_value")
    private Boolean booleanValue;

    @JSONField(name = "integer_value")
    private Integer integerValue;

    @JSONField(name = "long_value")
    private Long longValue;

    @JSONField(name = "double_value")
    private Double doubleValue;

    @JSONField(name = "date_value")
    private Date dateValue;

    public WebInputNodeVariableUpsertInfo() {
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
        return "WebInputNodeVariableUpsertInfo{" +
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
