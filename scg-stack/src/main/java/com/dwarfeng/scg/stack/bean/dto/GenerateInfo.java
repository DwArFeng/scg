package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;

/**
 * 生成信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GenerateInfo implements Dto {

    private static final long serialVersionUID = 7007573806748800142L;

    private Date lastGeneratedDate;
    private Integer lastIndex;
    private int deviceId;
    private boolean distributed;

    public GenerateInfo() {
    }

    public GenerateInfo(Date lastGeneratedDate, Integer lastIndex, int deviceId, boolean distributed) {
        this.lastGeneratedDate = lastGeneratedDate;
        this.lastIndex = lastIndex;
        this.deviceId = deviceId;
        this.distributed = distributed;
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

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    @Override
    public String toString() {
        return "GenerateInfo{" +
                "lastGeneratedDate=" + lastGeneratedDate +
                ", lastIndex=" + lastIndex +
                ", deviceId=" + deviceId +
                ", distributed=" + distributed +
                '}';
    }
}
