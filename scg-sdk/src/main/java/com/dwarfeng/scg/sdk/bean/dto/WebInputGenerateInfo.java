package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 生成信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputGenerateInfo implements Dto {

    private static final long serialVersionUID = -5497978855141305180L;

    public static GenerateInfo toStackBean(WebInputGenerateInfo webInputGenerateInfo) {
        if (Objects.isNull(webInputGenerateInfo)) {
            return null;
        } else {
            return new GenerateInfo(
                    WebInputStringIdKey.toStackBean(webInputGenerateInfo.getScgSettingKey())
            );
        }
    }

    @JSONField(name = "scg_setting_key")
    @NotNull
    @Valid
    private WebInputStringIdKey scgSettingKey;

    public WebInputGenerateInfo() {
    }

    public WebInputStringIdKey getScgSettingKey() {
        return scgSettingKey;
    }

    public void setScgSettingKey(WebInputStringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public String toString() {
        return "WebInputGenerateInfo{" +
                "scgSettingKey=" + scgSettingKey +
                '}';
    }
}
