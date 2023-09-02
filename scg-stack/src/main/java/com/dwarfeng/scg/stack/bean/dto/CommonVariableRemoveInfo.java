package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 公共变量移除信息。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class CommonVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 2725453395898209373L;

    private String scgSettingId;
    private String variableId;

    public CommonVariableRemoveInfo() {
    }

    public CommonVariableRemoveInfo(String scgSettingId, String variableId) {
        this.scgSettingId = scgSettingId;
        this.variableId = variableId;
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
        return "CommonVariableRemoveInfo{" +
                "scgSettingId='" + scgSettingId + '\'' +
                ", variableId='" + variableId + '\'' +
                '}';
    }
}
