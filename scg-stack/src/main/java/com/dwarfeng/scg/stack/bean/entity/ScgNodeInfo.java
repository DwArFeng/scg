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

    private static final long serialVersionUID = -7408054568430817945L;

    private ScgNodeKey key;
    private Date lastGeneratedDate;
    private Date lastIndex;

    public ScgNodeInfo() {
    }

    public ScgNodeInfo(ScgNodeKey key, Date lastGeneratedDate, Date lastIndex) {
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

    public Date getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Date lastIndex) {
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
