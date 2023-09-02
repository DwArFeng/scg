package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableRemoveInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 节点变量移除信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputNodeVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 1218333577115736415L;

    public static NodeVariableRemoveInfo toStackBean(WebInputNodeVariableRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NodeVariableRemoveInfo(
                    webInput.getScgSettingId(),
                    webInput.getDeviceId(),
                    webInput.getVariableId()
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

    public WebInputNodeVariableRemoveInfo() {
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
        return "WebInputNodeVariableRemoveInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
