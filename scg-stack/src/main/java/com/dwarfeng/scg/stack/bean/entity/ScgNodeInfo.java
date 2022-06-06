package com.dwarfeng.scg.stack.bean.entity;

import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

import java.util.Date;

/**
 * 流水码生成节点信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ScgNodeInfo implements Entity<ScgNodeKey> {

    private static final long serialVersionUID = -8112439275851557120L;

    private ScgNodeKey key;
    private Date lastGeneratedDate;
    private Integer lastIndex;

    public ScgNodeInfo() {
    }

    public ScgNodeInfo(ScgNodeKey key, Date lastGeneratedDate, Integer lastIndex) {
        this.key = key;
        this.lastGeneratedDate = lastGeneratedDate;
        this.lastIndex = lastIndex;
    }

    @Override
    public ScgNodeKey getKey() {
        return key;
    }

    @Override
    public void setKey(ScgNodeKey key) {
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
        return "ScgNodeInfo{" +
                "key=" + key +
                ", lastGeneratedDate=" + lastGeneratedDate +
                ", lastIndex=" + lastIndex +
                '}';
    }
}
