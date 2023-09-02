package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.bean.dto.NodeVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 节点变量操作处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface NodeVariableOperateHandler extends Handler {

    /**
     * 查看指定的节点变量。
     *
     * <p>
     * 如果指定的节点变量不存在，则返回 null。
     *
     * @param inspectInfo 查看信息。
     * @return 指定的节点变量。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    NodeVariable inspect(NodeVariableInspectInfo inspectInfo) throws HandlerException;

    /**
     * 向指定的节点变量中插入或更新指定的值。
     *
     * @param upsertInfo 插入更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsert(NodeVariableUpsertInfo upsertInfo) throws HandlerException;

    /**
     * 从指定的节点变量中移除指定的值。
     *
     * <p>
     * 如果指定的节点变量不存在，则什么都不做。
     *
     * @param removeInfo 移除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(NodeVariableRemoveInfo removeInfo) throws HandlerException;
}
