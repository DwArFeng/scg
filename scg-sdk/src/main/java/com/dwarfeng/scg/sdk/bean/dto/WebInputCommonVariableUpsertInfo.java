package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 公共变量插入更新信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputCommonVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = -3907815460264849734L;

    public static CommonVariableUpsertInfo toStackBean(WebInputCommonVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new CommonVariableUpsertInfo(
                    webInput.getScgSettingId(),
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

    public WebInputCommonVariableUpsertInfo() {
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
        return "WebInputCommonVariableUpsertInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
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
