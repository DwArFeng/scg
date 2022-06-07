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

    private static final long serialVersionUID = -5247157394934901904L;

    private Date lastGeneratedDate;
    private Integer lastIndex;
    private int deviceId;

    public GenerateInfo() {
    }

    public GenerateInfo(Date lastGeneratedDate, Integer lastIndex, int deviceId) {
        this.lastGeneratedDate = lastGeneratedDate;
        this.lastIndex = lastIndex;
        this.deviceId = deviceId;
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

    @Override
    public String toString() {
        return "GenerateInfo{" +
                "lastGeneratedDate=" + lastGeneratedDate +
                ", lastIndex=" + lastIndex +
                ", deviceId=" + deviceId +
                '}';
    }
}
