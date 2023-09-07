package com.dwarfeng.scg.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 流水码生成设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonScgSetting implements Bean {

    private static final long serialVersionUID = 9136992175234162516L;

    public static FastJsonScgSetting of(ScgSetting scgSetting) {
        if (Objects.isNull(scgSetting)) {
            return null;
        } else {
            return new FastJsonScgSetting(
                    FastJsonStringIdKey.of(scgSetting.getKey()),
                    scgSetting.getLabel(), scgSetting.getRemark(), scgSetting.getType(), scgSetting.getParam(),
                    scgSetting.isEnabled(), scgSetting.getGranularity()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "enabled", ordinal = 6)
    private boolean enabled;

    @JSONField(name = "granularity", ordinal = 7)
    private int granularity;

    public FastJsonScgSetting() {
    }

    public FastJsonScgSetting(
            FastJsonStringIdKey key, String label, String remark, String type, String param, boolean enabled,
            int granularity
    ) {
        this.key = key;
        this.label = label;
        this.remark = remark;
        this.type = type;
        this.param = param;
        this.enabled = enabled;
        this.granularity = granularity;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonScgSetting{" +
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
