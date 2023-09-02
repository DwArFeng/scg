package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableInspectInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 节点变量查看信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class WebInputNodeVariableInspectInfo implements Dto {

    private static final long serialVersionUID = 8766900138944875697L;

    public static NodeVariableInspectInfo toStackBean(WebInputNodeVariableInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NodeVariableInspectInfo(
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

    public WebInputNodeVariableInspectInfo() {
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
        return "WebInputNodeVariableInspectInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", deviceId=" + deviceId +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
