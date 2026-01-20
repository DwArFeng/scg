package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.util.Constraints;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableRemoveInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 公共变量移除信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputCommonVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = -2584512002244399931L;

    public static CommonVariableRemoveInfo toStackBean(WebInputCommonVariableRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new CommonVariableRemoveInfo(
                    webInput.getScgSettingId(),
                    webInput.getVariableId()
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

    public WebInputCommonVariableRemoveInfo() {
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

    @Override
    public String toString() {
        return "WebInputCommonVariableRemoveInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
