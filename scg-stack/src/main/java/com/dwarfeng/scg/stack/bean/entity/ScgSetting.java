package com.dwarfeng.scg.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 流水码生成设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ScgSetting implements Entity<StringIdKey> {

    private static final long serialVersionUID = 2372182637830385290L;
    
    private StringIdKey key;
    private String label;
    private String remark;
    private String type;
    private String param;
    private boolean enabled;
    private int granularity;

    public ScgSetting() {
    }

    public ScgSetting(
            StringIdKey key, String label, String remark, String type, String param, boolean enabled, int granularity
    ) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.type = type;
        this.param = param;
        this.enabled = enabled;
        this.granularity = granularity;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getGranularity() {
        return granularity;
    }

    public void setGranularity(int granularity) {
        this.granularity = granularity;
    }

    @Override
    public String toString() {
        return "ScgSetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", enabled=" + enabled +
                ", granularity=" + granularity +
                '}';
    }
}
