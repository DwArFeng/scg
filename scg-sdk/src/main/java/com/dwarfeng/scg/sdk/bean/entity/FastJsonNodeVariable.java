package com.dwarfeng.scg.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.bean.key.FastJsonNodeVariableKey;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 节点变量。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class FastJsonNodeVariable implements Bean {

    private static final long serialVersionUID = 3949865500615593272L;

    public static FastJsonNodeVariable of(NodeVariable nodeVariable) {
        if (Objects.isNull(nodeVariable)) {
            return null;
        } else {
            return new FastJsonNodeVariable(
                    FastJsonNodeVariableKey.of(nodeVariable.getKey()),
                    nodeVariable.getStringValue(),
                    nodeVariable.getBooleanValue(),
                    nodeVariable.getIntegerValue(),
                    nodeVariable.getLongValue(),
                    nodeVariable.getDoubleValue(),
                    nodeVariable.getDateValue(),
                    nodeVariable.getLastUpdatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonNodeVariableKey key;

    @JSONField(name = "string_value", ordinal = 2)
    private String stringValue;

    @JSONField(name = "boolean_value", ordinal = 3)
    private Boolean booleanValue;

    @JSONField(name = "integer_value", ordinal = 4)
    private Integer integerValue;

    @JSONField(name = "long_value", ordinal = 5)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 6)
    private Double doubleValue;

    @JSONField(name = "date_value", ordinal = 7)
    private Date dateValue;

    @JSONField(name = "last_updated_date", ordinal = 8)
    private Date lastUpdatedDate;

    public FastJsonNodeVariable() {
    }

    public FastJsonNodeVariable(
            FastJsonNodeVariableKey key, String stringValue, Boolean booleanValue, Integer integerValue,
            Long longValue, Double doubleValue, Date dateValue, Date lastUpdatedDate
    ) {
        this.key = key;
        this.stringValue = stringValue;
        this.booleanValue = booleanValue;
        this.integerValue = integerValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.dateValue = dateValue;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public FastJsonNodeVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonNodeVariableKey key) {
        this.key = key;
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

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "FastJsonNodeVariable{" +
                "key=" + key +
                ", stringValue='" + stringValue + '\'' +
                ", booleanValue=" + booleanValue +
                ", integerValue=" + integerValue +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", dateValue=" + dateValue +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
