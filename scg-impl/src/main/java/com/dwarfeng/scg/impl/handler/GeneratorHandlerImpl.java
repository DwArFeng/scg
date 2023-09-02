package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.sdk.util.CommonVariableUtil;
import com.dwarfeng.scg.sdk.util.NodeVariableUtil;
import com.dwarfeng.scg.stack.bean.dto.*;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.scg.stack.exception.SerialCodeGranularityMismatchException;
import com.dwarfeng.scg.stack.exception.UnsupportedGeneratorTypeException;
import com.dwarfeng.scg.stack.handler.CommonVariableOperateHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.Generator.SerialCodeGranularity;
import com.dwarfeng.scg.stack.handler.Generator.VariableType;
import com.dwarfeng.scg.stack.handler.GeneratorHandler;
import com.dwarfeng.scg.stack.handler.NodeVariableOperateHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.*;

@Component
public class GeneratorHandlerImpl implements GeneratorHandler {

    private static final Set<SerialCodeGranularity> VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE =
            Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
                    SerialCodeGranularity.SETTING,
                    SerialCodeGranularity.DEVICE
            )));
    private static final Set<SerialCodeGranularity> VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE =
            Collections.unmodifiableSet(new HashSet<>(Collections.singletonList(
                    SerialCodeGranularity.SETTING
            )));

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorHandlerImpl.class);

    private final ApplicationContext ctx;

    private final NodeVariableOperateHandler nodeVariableOperateHandler;
    private final CommonVariableOperateHandler commonVariableOperateHandler;

    private final List<GeneratorMaker> generatorMakers;

    @Value("${generate.device_id}")
    private int deviceId;

    public GeneratorHandlerImpl(
            ApplicationContext ctx,
            NodeVariableOperateHandler nodeVariableOperateHandler,
            CommonVariableOperateHandler commonVariableOperateHandler,
            List<GeneratorMaker> generatorMakers
    ) {
        this.ctx = ctx;
        this.nodeVariableOperateHandler = nodeVariableOperateHandler;
        this.commonVariableOperateHandler = commonVariableOperateHandler;
        this.generatorMakers = Optional.ofNullable(generatorMakers).orElse(Collections.emptyList());
    }

    @Override
    public Generator make(StringIdKey scgSettingKey, String type, String param) throws GeneratorException {
        try {
            // 生成生成器。
            LOGGER.debug("通过生成器信息构建新的的生成器...");
            GeneratorMaker generatorMaker = generatorMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedGeneratorTypeException(type));
            Generator generator = generatorMaker.makeGenerator(type, param);
            LOGGER.debug("生成器构建成功!");
            String scgSettingId = scgSettingKey.getStringId();
            SerialCodeGranularity serialCodeGranularity = generator.getSerialCodeGranularity();
            Generator.Context context = ctx.getBean(
                    InternalGeneratorContext.class, this, scgSettingId, deviceId, serialCodeGranularity
            );
            generator.init(context);
            LOGGER.debug("生成器初始化成功!");
            LOGGER.debug("生成器: " + generator);
            return generator;
        } catch (GeneratorException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public class InternalGeneratorContext implements Generator.Context {

        private final String scgSettingId;
        private final Integer deviceId;

        private final SerialCodeGranularity serialCodeGranularity;

        // 该构造器方法不是用于 Spring 自动注入的。
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
        public InternalGeneratorContext(
                String scgSettingId, Integer deviceId, SerialCodeGranularity serialCodeGranularity
        ) {
            this.scgSettingId = scgSettingId;
            this.deviceId = deviceId;
            this.serialCodeGranularity = serialCodeGranularity;
        }

        @BehaviorAnalyse
        @Override
        public int getDeviceId() {
            return deviceId;
        }

        @SuppressWarnings("DuplicatedCode")
        @BehaviorAnalyse
        @Override
        public Object inspectNodeVariable(@Nonnull String variableId, @Nonnull VariableType variableType)
                throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
            NodeVariableInspectInfo inspectInfo = new NodeVariableInspectInfo(
                    scgSettingId, deviceId, variableId
            );
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

        @BehaviorAnalyse
        @Override
        public void upsertNodeVariable(@Nonnull String variableId, @Nonnull VariableType variableType, Object value)
                throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
            NodeVariableUpsertInfo upsertInfo;
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

        @BehaviorAnalyse
        @Override
        public void removeNodeVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId
        ) throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_NODE_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
            NodeVariableRemoveInfo removeInfo = new NodeVariableRemoveInfo(
                    scgSettingId, deviceId, variableId
            );
            nodeVariableOperateHandler.remove(removeInfo);
        }

        @SuppressWarnings("DuplicatedCode")
        @BehaviorAnalyse
        @Override
        public Object inspectCommonVariable(
                @Nonnull String variableId, @Nonnull VariableType variableType
        ) throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
            CommonVariableInspectInfo inspectInfo = new CommonVariableInspectInfo(
                    scgSettingId, variableId
            );
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

        @BehaviorAnalyse
        @Override
        public void upsertCommonVariable(
                @Nonnull String variableId, @Nonnull VariableType variableType, Object value
        ) throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
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

        @BehaviorAnalyse
        @Override
        public void removeCommonVariable(@Nonnull String variableId) throws Exception {
            // 检查流水码粒度是否匹配。
            if (!VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE.contains(serialCodeGranularity)) {
                throw new SerialCodeGranularityMismatchException(
                        VALID_SERIAL_CODE_GRANULARITIES_COMMON_VARIABLE, serialCodeGranularity
                );
            }

            // 执行动作。
            CommonVariableRemoveInfo removeInfo = new CommonVariableRemoveInfo(
                    scgSettingId, variableId
            );
            commonVariableOperateHandler.remove(removeInfo);
        }

        @Override
        public String toString() {
            return "InternalGeneratorContext{" +
                    "nodeVariableOperateHandler=" + nodeVariableOperateHandler +
                    ", commonVariableOperateHandler=" + commonVariableOperateHandler +
                    ", scgSettingId='" + scgSettingId + '\'' +
                    ", deviceId=" + deviceId +
                    ", serialCodeGranularity=" + serialCodeGranularity +
                    '}';
        }
    }
}
