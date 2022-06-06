package com.dwarfeng.scg.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.scg.sdk.bean.key.FastJsonScgNodeKey;
import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 流水码生成节点信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonScgNodeInfo implements Bean {

    private static final long serialVersionUID = 4810551731422236903L;

    public static FastJsonScgNodeInfo of(ScgNodeInfo scgNodeInfo) {
        if (Objects.isNull(scgNodeInfo)) {
            return null;
        } else {
            return new FastJsonScgNodeInfo(
                    FastJsonScgNodeKey.of(scgNodeInfo.getKey()),
                    scgNodeInfo.getLastGeneratedDate(), scgNodeInfo.getLastIndex()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonScgNodeKey key;

    @JSONField(name = "last_generated_date", ordinal = 2)
    private Date lastGeneratedDate;

    @JSONField(name = "last_index", ordinal = 3)
    private Integer lastIndex;

    public FastJsonScgNodeInfo() {
    }

    public FastJsonScgNodeInfo(FastJsonScgNodeKey key, Date lastGeneratedDate, Integer lastIndex) {
        this.key = key;
        this.lastGeneratedDate = lastGeneratedDate;
        this.lastIndex = lastIndex;
    }

    public FastJsonScgNodeKey getKey() {
        return key;
    }

    public void setKey(FastJsonScgNodeKey key) {
        this.key = key;
    }

    public Date getLastGeneratedDate() {
        return lastGeneratedDate;
    }

    public void setLastGeneratedDate(Date lastGeneratedDate) {
        this.lastGeneratedDate = lastGeneratedDate;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }

    @Override
    public String toString() {
        return "FastJsonScgNodeInfo{" +
                "key=" + key +
                ", lastGeneratedDate=" + lastGeneratedDate +
                ", lastIndex=" + lastIndex +
                '}';
    }
}
