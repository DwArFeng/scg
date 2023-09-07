package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.sdk.util.CommonVariableUtil;
import com.dwarfeng.scg.sdk.util.NodeVariableUtil;
import com.dwarfeng.scg.stack.bean.dto.*;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.UnsupportedGeneratorTypeException;
import com.dwarfeng.scg.stack.handler.CommonVariableOperateHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.Generator.VariableType;
import com.dwarfeng.scg.stack.handler.GeneratorHandler;
import com.dwarfeng.scg.stack.handler.NodeVariableOperateHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.*;

@Component
public class GeneratorHandlerImpl implements GeneratorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorHandlerImpl.class);

    private final NodeVariableOperateHandler nodeVariableOperateHandler;
    private final CommonVariableOperateHandler commonVariableOperateHandler;

    private final List<GeneratorMaker> generatorMakers;

    @Value("${generate.device_id}")
    private int deviceId;

    private final InternalGeneratorContext generatorContext = new InternalGeneratorContext();

    public GeneratorHandlerImpl(
            NodeVariableOperateHandler nodeVariableOperateHandler,
            CommonVariableOperateHandler commonVariableOperateHandler,
            List<GeneratorMaker> generatorMakers
    ) {
        this.nodeVariableOperateHandler = nodeVariableOperateHandler;
        this.commonVariableOperateHandler = commonVariableOperateHandler;
        this.generatorMakers = Optional.ofNullable(generatorMakers).orElse(Collections.emptyList());
    }

    @Override
    public Generator make(String type, String param) throws GeneratorException {
        try {
            // 生成生成器。
            LOGGER.debug("通过生成器信息构建新的的生成器...");
            GeneratorMaker generatorMaker = generatorMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedGeneratorTypeException(type));
            Generator generator = generatorMaker.makeGenerator(type, param);
            LOGGER.debug("生成器构建成功!");
            generator.init(generatorContext);
            LOGGER.debug("生成器初始化成功!");
            LOGGER.debug("生成器: " + generator);
            return generator;
        } catch (GeneratorException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

    private class InternalGeneratorContext implements Generator.Context {

        @BehaviorAnalyse
        @Override
        public int getDeviceId() {
            return deviceId;
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public Object inspectNodeVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType
        ) throws Exception {
            // 执行动作。
            String scgSettingId = scgSettingKey.getStringId();
            NodeVariableInspectInfo inspectInfo = new NodeVariableInspectInfo(scgSettingId, deviceId, variableId);
            NodeVariable nodeVariable = nodeVariableOperateHandler.inspect(inspectInfo);
            if (Objects.isNull(nodeVariable)) {
                return null;
            }
            switch (variableType) {
                case STRING:
                    return nodeVariable.getStringValue();
                case BOOLEAN:
                    return nodeVariable.getBooleanValue();
                case INTEGER:
                    return nodeVariable.getIntegerValue();
                case LONG:
                    return nodeVariable.getLongValue();
                case DOUBLE:
                    return nodeVariable.getDoubleValue();
                case DATE:
                    return nodeVariable.getDateValue();
                default:
                    throw new IllegalArgumentException("非法的变量类型: " + variableType);
            }
        }

        @Override
        public void upsertNodeVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType,
                Object value
        ) throws Exception {
            // 执行动作。
            NodeVariableUpsertInfo upsertInfo;
            String scgSettingId = scgSettingKey.getStringId();
            switch (variableType) {
                case STRING:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (String) value);
                    break;
                case BOOLEAN:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (Boolean) value);
                    break;
                case INTEGER:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (Integer) value);
                    break;
                case LONG:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (Long) value);
                    break;
                case DOUBLE:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (Double) value);
                    break;
                case DATE:
                    upsertInfo = NodeVariableUtil.ofUpsertInfo(scgSettingId, deviceId, variableId, (Date) value);
                    break;
                default:
                    throw new IllegalArgumentException("非法的变量类型: " + variableType);
            }
            nodeVariableOperateHandler.upsert(upsertInfo);
        }

        @Override
        public void removeNodeVariable(@Nonnull StringIdKey scgSettingKey, @Nonnull String variableId)
                throws Exception {
            // 执行动作。
            String scgSettingId = scgSettingKey.getStringId();
            NodeVariableRemoveInfo removeInfo = new NodeVariableRemoveInfo(scgSettingId, deviceId, variableId);
            nodeVariableOperateHandler.remove(removeInfo);
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        public Object inspectCommonVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType
        ) throws Exception {
            // 执行动作。
            String scgSettingId = scgSettingKey.getStringId();
            CommonVariableInspectInfo inspectInfo = new CommonVariableInspectInfo(scgSettingId, variableId);
            CommonVariable commonVariable = commonVariableOperateHandler.inspect(inspectInfo);
            if (Objects.isNull(commonVariable)) {
                return null;
            }
            switch (variableType) {
                case STRING:
                    return commonVariable.getStringValue();
                case BOOLEAN:
                    return commonVariable.getBooleanValue();
                case INTEGER:
                    return commonVariable.getIntegerValue();
                case LONG:
                    return commonVariable.getLongValue();
                case DOUBLE:
                    return commonVariable.getDoubleValue();
                case DATE:
                    return commonVariable.getDateValue();
                default:
                    throw new IllegalArgumentException("非法的变量类型: " + variableType);
            }
        }

        @Override
        public void upsertCommonVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType,
                Object value
        ) throws Exception {
            // 执行动作。
            String scgSettingId = scgSettingKey.getStringId();
            CommonVariableUpsertInfo upsertInfo;
            switch (variableType) {
                case STRING:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (String) value);
                    break;
                case BOOLEAN:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (Boolean) value);
                    break;
                case INTEGER:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (Integer) value);
                    break;
                case LONG:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (Long) value);
                    break;
                case DOUBLE:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (Double) value);
                    break;
                case DATE:
                    upsertInfo = CommonVariableUtil.ofUpsertInfo(scgSettingId, variableId, (Date) value);
                    break;
                default:
                    throw new IllegalArgumentException("非法的变量类型: " + variableType);
            }
            commonVariableOperateHandler.upsert(upsertInfo);
        }

        @Override
        public void removeCommonVariable(@Nonnull @NotNull StringIdKey scgSettingKey, @Nonnull String variableId)
                throws Exception {
            // 执行动作。
            String scgSettingId = scgSettingKey.getStringId();
            CommonVariableRemoveInfo removeInfo = new CommonVariableRemoveInfo(scgSettingId, variableId);
            commonVariableOperateHandler.remove(removeInfo);
        }
    }
}
