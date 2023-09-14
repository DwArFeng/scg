package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 批量生成信息。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public class BatchGenerateInfo implements Dto {

    private static final long serialVersionUID = 6246257913605005772L;
    
    private StringIdKey scgSettingKey;
    private int size;

    public BatchGenerateInfo() {
    }

    public BatchGenerateInfo(StringIdKey scgSettingKey, int size) {
        this.scgSettingKey = scgSettingKey;
        this.size = size;
    }

    public StringIdKey getScgSettingKey() {
        return scgSettingKey;
    }

    public void setScgSettingKey(StringIdKey scgSettingKey) {
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
        return "BatchGenerateInfo{" +
                "scgSettingKey=" + scgSettingKey +
                ", size=" + size +
                '}';
    }
}
