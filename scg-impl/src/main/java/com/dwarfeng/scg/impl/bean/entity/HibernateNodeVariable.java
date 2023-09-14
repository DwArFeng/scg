package com.dwarfeng.scg.impl.bean.entity;

import com.dwarfeng.scg.impl.bean.key.HibernateNodeVariableKey;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(HibernateNodeVariableKey.class)
@Table(name = "tbl_node_variable")
public class HibernateNodeVariable implements Bean {

    private static final long serialVersionUID = 20730736142239734L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "scg_setting_id", length = Constraints.LENGTH_ID, nullable = false)
    private String scgSettingId;

    @Id
    @Column(name = "device_id", nullable = false)
    private Integer deviceId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_ID, nullable = false)
    private String variableId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "string_value", columnDefinition = "TEXT")
    private String stringValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    @Column(name = "integer_value")
    private Integer integerValue;

    @Column(name = "long_value")
    private Long longValue;

    @Column(name = "double_value")
    private Double doubleValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_value")
    private Date dateValue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateScgSetting.class)
    @JoinColumns({ //
            @JoinColumn(name = "scg_setting_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateScgSetting scgSetting;

    public HibernateNodeVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateNodeVariableKey getKey() {
        return new HibernateNodeVariableKey(scgSettingId, deviceId, variableId);
    }

    public void setKey(HibernateNodeVariableKey key) {
        if (Objects.isNull(key)) {
            this.scgSettingId = null;
            this.deviceId = null;
            this.variableId = null;
        } else {
            this.scgSettingId = key.getScgSettingId();
            this.deviceId = key.getDeviceId();
            this.variableId = key.getVariableId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
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

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public HibernateScgSetting getScgSetting() {
        return scgSetting;
    }

    public void setScgSetting(HibernateScgSetting scgSetting) {
        this.scgSetting = scgSetting;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "scgSettingId = " + scgSettingId + ", " +
                "deviceId = " + deviceId + ", " +
                "variableId = " + variableId + ", " +
                "stringValue = " + stringValue + ", " +
                "booleanValue = " + booleanValue + ", " +
                "integerValue = " + integerValue + ", " +
                "longValue = " + longValue + ", " +
                "doubleValue = " + doubleValue + ", " +
                "dateValue = " + dateValue + ", " +
                "lastUpdatedDate = " + lastUpdatedDate + ", " +
                "scgSetting = " + scgSetting + ")";
    }
}
