package com.dwarfeng.scg.stack.bean.entity;

import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

import java.util.Date;

/**
 * 公共变量。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class CommonVariable implements Entity<CommonVariableKey> {

    private static final long serialVersionUID = -2004420850740795197L;

    private CommonVariableKey key;
    private String stringValue;
    private Boolean booleanValue;
    private Integer integerValue;
    private Long longValue;
    private Double doubleValue;
    private Date dateValue;
    private Date lastUpdatedDate;

    public CommonVariable() {
    }

    public CommonVariable(
            CommonVariableKey key, String stringValue, Boolean booleanValue, Integer integerValue, Long longValue,
            Double doubleValue, Date dateValue, Date lastUpdatedDate
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

    @Override
    public CommonVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(CommonVariableKey key) {
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
        return "CommonVariable{" +
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
