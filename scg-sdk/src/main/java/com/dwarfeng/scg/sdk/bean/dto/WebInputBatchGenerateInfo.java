package com.dwarfeng.scg.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.stack.bean.dto.BatchGenerateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * 批量生成信息。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class WebInputBatchGenerateInfo implements Dto {

    private static final long serialVersionUID = 8565196387683275934L;

    public static BatchGenerateInfo toStackBean(WebInputBatchGenerateInfo webInputBatchGenerateInfo) {
        if (Objects.isNull(webInputBatchGenerateInfo)) {
            return null;
        } else {
            return new BatchGenerateInfo(
                    WebInputStringIdKey.toStackBean(webInputBatchGenerateInfo.getScgSettingKey()),
                    webInputBatchGenerateInfo.getSize()
            );
        }
    }

    @JSONField(name = "scg_setting_key")
    @NotNull
    @Valid
    private WebInputStringIdKey scgSettingKey;

    @JSONField(name = "size")
    @Positive
    private int size;

    public WebInputBatchGenerateInfo() {
    }

    public WebInputStringIdKey getScgSettingKey() {
        return scgSettingKey;
    }

    public void setScgSettingKey(WebInputStringIdKey scgSettingKey) {
        this.scgSettingKey = scgSettingKey;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "WebInputBatchGenerateInfo{" +
                "scgSettingKey=" + scgSettingKey +
                ", size=" + size +
                '}';
    }
}
