package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.bean.dto.CommonVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 公共变量操作处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface CommonVariableOperateHandler extends Handler {

    /**
     * 查看指定的公共变量。
     *
     * <p>
     * 如果指定的公共变量不存在，则返回 null。
     *
     * @param inspectInfo 查看信息。
     * @return 指定的公共变量。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    CommonVariable inspect(CommonVariableInspectInfo inspectInfo) throws HandlerException;

    /**
     * 向指定的公共变量中插入或更新指定的值。
     *
     * @param upsertInfo 插入更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(CommonVariableUpsertInfo upsertInfo) throws HandlerException;

    /**
     * 从指定的公共变量中移除指定的值。
     *
     * <p>
     * 如果指定的公共变量不存在，则什么都不做。
     *
     * @param removeInfo 移除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(CommonVariableRemoveInfo removeInfo) throws HandlerException;
}
