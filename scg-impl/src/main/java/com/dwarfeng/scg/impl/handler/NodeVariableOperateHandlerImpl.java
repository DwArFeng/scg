package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.dto.NodeVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.NodeVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.scg.stack.handler.NodeVariableOperateHandler;
import com.dwarfeng.scg.stack.service.NodeVariableMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;

@Component
public class NodeVariableOperateHandlerImpl implements NodeVariableOperateHandler {

    private final NodeVariableMaintainService nodeVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public NodeVariableOperateHandlerImpl(
            NodeVariableMaintainService nodeVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.nodeVariableMaintainService = nodeVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Nullable
    @Override
    public NodeVariable inspect(NodeVariableInspectInfo inspectInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = inspectInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            Integer deviceId = inspectInfo.getDeviceId();
            String variableId = inspectInfo.getVariableId();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 NodeVariableKey。
            NodeVariableKey nodeVariableKey = new NodeVariableKey(scgSettingId, deviceId, variableId);

            // 查询并返回节点变量。
            return nodeVariableMaintainService.getIfExists(nodeVariableKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void upsert(NodeVariableUpsertInfo upsertInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = upsertInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            Integer deviceId = upsertInfo.getDeviceId();
            String variableId = upsertInfo.getVariableId();
            String stringValue = upsertInfo.getStringValue();
            Boolean booleanValue = upsertInfo.getBooleanValue();
            Integer integerValue = upsertInfo.getIntegerValue();
            Long longValue = upsertInfo.getLongValue();
            Double doubleValue = upsertInfo.getDoubleValue();
            Date dateValue = upsertInfo.getDateValue();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 NodeVariable。
            NodeVariableKey nodeVariableKey = new NodeVariableKey(scgSettingId, deviceId, variableId);
            NodeVariable nodeVariable = new NodeVariable(
                    nodeVariableKey, stringValue, booleanValue, integerValue, longValue, doubleValue, dateValue,
                    new Date()
            );

            // 插入或更新节点变量。
            nodeVariableMaintainService.insertOrUpdate(nodeVariable);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void remove(NodeVariableRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = removeInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            Integer deviceId = removeInfo.getDeviceId();
            String variableId = removeInfo.getVariableId();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 NodeVariableKey。
            NodeVariableKey nodeVariableKey = new NodeVariableKey(scgSettingId, deviceId, variableId);

            // 删除节点变量。
            nodeVariableMaintainService.deleteIfExists(nodeVariableKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
