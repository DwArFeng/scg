package com.dwarfeng.scg.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 生成结果。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class GenerateResult implements Dto {

    private static final long serialVersionUID = -986356399105512853L;

    private String serialCode;
    private int neoIndex;

    public GenerateResult() {
    }

    public GenerateResult(String serialCode, int neoIndex) {
        this.serialCode = serialCode;
        this.neoIndex = neoIndex;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public int getNeoIndex() {
        return neoIndex;
    }

    public void setNeoIndex(int neoIndex) {
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
