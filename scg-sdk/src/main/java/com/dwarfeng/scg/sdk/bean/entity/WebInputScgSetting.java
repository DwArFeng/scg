package com.dwarfeng.scg.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 流水码生成设置。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputScgSetting implements Bean {

    private static final long serialVersionUID = 8155988937871052791L;

    public static ScgSetting toStackBean(WebInputScgSetting webInputScgSetting) {
        if (Objects.isNull(webInputScgSetting)) {
            return null;
        } else {
            return new ScgSetting(
                    WebInputStringIdKey.toStackBean(webInputScgSetting.getKey()),
                    webInputScgSetting.getLabel(), webInputScgSetting.getRemark(), webInputScgSetting.getType(),
                    webInputScgSetting.getParam(), webInputScgSetting.isEnabled(), webInputScgSetting.isDistributed()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "distributed")
    private boolean distributed;

    public WebInputScgSetting() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    @Override
    public String toString() {
        return "WebInputScgSetting{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", enabled=" + enabled +
                ", distributed=" + distributed +
                '}';
    }
}
