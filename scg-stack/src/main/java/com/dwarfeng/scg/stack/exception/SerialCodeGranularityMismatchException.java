package com.dwarfeng.scg.stack.exception;

import com.dwarfeng.scg.stack.handler.Generator.SerialCodeGranularity;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

import java.util.Collection;
import java.util.List;

/**
 * 序列码粒度不匹配异常。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class SerialCodeGranularityMismatchException extends HandlerException {

    private static final long serialVersionUID = -2013522677383891069L;

    private final Collection<SerialCodeGranularity> expectedGranularities;
    private final SerialCodeGranularity actualGranularity;

    public SerialCodeGranularityMismatchException(
            Collection<SerialCodeGranularity> expectedGranularities, SerialCodeGranularity actualGranularity
    ) {
        this.expectedGranularities = expectedGranularities;
        this.actualGranularity = actualGranularity;
    }

    public SerialCodeGranularityMismatchException(
            Throwable cause, List<SerialCodeGranularity> expectedGranularities, SerialCodeGranularity actualGranularity
    ) {
        super(cause);
        this.expectedGranularities = expectedGranularities;
        this.actualGranularity = actualGranularity;
    }

    @Override
    public String getMessage() {
        return "序列码粒度不匹配: 期望为 " + expectedGranularities + ", 实际为 " + actualGranularity;
    }
}
