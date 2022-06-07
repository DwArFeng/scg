package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 生成结果。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GenerateResult implements Dto {

    private static final long serialVersionUID = 5178075178186609277L;

    private String serialCode;
    private Integer neoIndex;

    public GenerateResult() {
    }

    public GenerateResult(String serialCode, Integer neoIndex) {
        this.serialCode = serialCode;
        this.neoIndex = neoIndex;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public Integer getNeoIndex() {
        return neoIndex;
    }

    public void setNeoIndex(Integer neoIndex) {
        this.neoIndex = neoIndex;
    }

    @Override
    public String toString() {
        return "GenerateResult{" +
                "serialCode='" + serialCode + '\'' +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
