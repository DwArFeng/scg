package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.dto.NodeVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.handler.NodeVariableOperateHandler;
import com.dwarfeng.scg.stack.service.NodeVariableOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class NodeVariableOperateServiceImpl implements NodeVariableOperateService {

    private final NodeVariableOperateHandler nodeVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public NodeVariableOperateServiceImpl(
            NodeVariableOperateHandler nodeVariableOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.nodeVariableOperateHandler = nodeVariableOperateHandler;
        this.sem = sem;
    }

    @Nullable
    @Override
    public NodeVariable inspect(NodeVariableInspectInfo inspectInfo) throws ServiceException {
        try {
            return nodeVariableOperateHandler.inspect(inspectInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看指定的节点变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsert(NodeVariableUpsertInfo upsertInfo) throws ServiceException {
        try {
            nodeVariableOperateHandler.upsert(upsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    "向指定的节点变量中插入或更新指定的值时发生异常", LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void remove(NodeVariableRemoveInfo removeInfo) throws ServiceException {
        try {
            nodeVariableOperateHandler.remove(removeInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("从指定的节点变量中移除指定的值时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
