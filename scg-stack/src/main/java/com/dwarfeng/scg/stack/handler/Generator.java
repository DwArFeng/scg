package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.exception.GeneratorException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 生成器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Generator {

    /**
     * 初始化生成器。
     *
     * <p>
     * 该方法会在生成器初始化后调用，请将 context 存放在生成器的字段中。<br>
     * 当生成器被触发后，执行上下文中的相应方法即可。
     *
     * @param context 生成器的上下文。
     */
    void init(Context context);

    /**
     * 生成序列码。
     *
     * @param contextInfo 上下文信息。
     * @return 生成的序列码。
     * @throws GeneratorException 生成器异常。
     */
    String generate(ContextInfo contextInfo) throws GeneratorException;

    /**
     * 生成序列码。
     *
     * @param contextInfo 上下文信息。
     * @param size        生成数量。
     * @return 生成的序列码组成的列表。
     * @throws GeneratorException 生成器异常。
     */
    List<String> generate(ContextInfo contextInfo, int size) throws GeneratorException;

    /**
     * 生成器上下文。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    interface Context {

        /**
         * 获取设备 ID。
         *
         * @return 设备 ID。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        int getDeviceId() throws Exception;

        /**
         * 查看节点变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @param variableType  变量类型。
         * @return 节点变量的值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        Object inspectNodeVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType
        ) throws Exception;

        /**
         * 插入/更新节点变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @param variableType  变量类型。
         * @param value         变量值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertNodeVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType,
                Object value
        ) throws Exception;

        /**
         * 移除节点变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeNodeVariable(@Nonnull StringIdKey scgSettingKey, @Nonnull String variableId) throws Exception;

        /**
         * 查看公共变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @param variableType  变量类型。
         * @return 公共变量的值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        Object inspectCommonVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType
        ) throws Exception;

        /**
         * 插入/更新公共变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @param variableType  变量类型。
         * @param value         变量值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertCommonVariable(
                @Nonnull StringIdKey scgSettingKey, @Nonnull String variableId, @Nonnull VariableType variableType,
                Object value
        ) throws Exception;

        /**
         * 移除公共变量。
         *
         * @param scgSettingKey 流水码生成设置的主键。
         * @param variableId    变量 ID。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeCommonVariable(@Nonnull StringIdKey scgSettingKey, @Nonnull String variableId) throws Exception;
    }

    /**
     * 变量类型。
     *
     * @author DwArFeng
     * @since 1.1.0
     */
    enum VariableType {
        STRING, BOOLEAN, INTEGER, LONG, DOUBLE, DATE,
    }

    /**
     * 上下文信息。
     *
     * @author DwArFeng
     * @since 1.2.0
     */
    final class ContextInfo {

        private final StringIdKey scgSettingKey;

        public ContextInfo(StringIdKey scgSettingKey) {
            this.scgSettingKey = scgSettingKey;
        }

        public StringIdKey getScgSettingKey() {
            return scgSettingKey;
        }

        @Override
        public String toString() {
            return "ContextInfo{" +
                    "scgSettingKey=" + scgSettingKey +
                    '}';
        }
    }
}
