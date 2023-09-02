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
     * 获取生成器的序列码粒度。
     *
     * <p>
     * 生成器的序列码粒度指示了生成器生成的序列码的唯一性程度，这种程序的不同会影响同步锁的等级，进而影响生成器的性能。
     *
     * <ul>
     *     <li>
     *         {@link SerialCodeGranularity#SETTING}:<br>
     *         生成器生成的序列码在同一个设置下是唯一的，在相同状态下，不同的设备生成的序列码是相同的。
     *         如果设备 ID 不参与序列码的生成，则生成的序列码则有可能是该粒度。<br>
     *         该粒度的序列码生成时，会在序列码生成设置级别下的分布式锁中完成，因此在一个集群中，至多只有一个生成方法在执行。<br>
     *         此粒度下，生成器访问节点变量和公共变量均被允许。<br>
     *         此粒度下，流水码的生成器效率较低，适合生顺序敏感且频率不高的序列码。<br>
     *         典型的序列码格式：1992-12-18-000123(年-月-日-当日序号)。
     *     </li>
     *     <li>
     *         {@link SerialCodeGranularity#DEVICE}:<br>
     *         生成器生成的序列码在同一个设备下是唯一的，在相同状态下，不同的设备生成的序列码是不同的。
     *         如果设备 ID 参与序列码的生成，则生成的序列码则一定是该粒度。<br>
     *         该粒度的序列码生成时，会在设备级别下的本地锁中完成，因此在一个集群中，每个设备可同时执行生成方法。<br>
     *         此粒度下，生成器允许访问节点变量，但不允许访问公共变量。<br>
     *         此粒度下，流水码的生成器效率较高，适合生顺序不敏感且频率较高的序列码。<br>
     *         典型的序列码格式：1992-12-18-01-000123(年-月-日-设备ID-当日序号)。
     *     </li>
     * </ul>
     *
     * <p>
     * 该方法需要在初始化之后立即可用。
     *
     * <p>
     * 该方法应该迅速返回，不得阻塞或执行耗时请求，且多次调用应该返回相同的值。
     *
     * @return 生成器的序列码粒度。
     */
    SerialCodeGranularity getSerialCodeGranularity();

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
     * @return 生成的序列码。
     * @throws GeneratorException 生成器异常。
     */
    String generate() throws GeneratorException;

    /**
     * 生成序列码。
     *
     * @param size 生成数量。
     * @return 生成的序列码组成的列表。
     * @throws GeneratorException 生成器异常。
     */
    List<String> generate(int size) throws GeneratorException;

    /**
     * 序列码生成器的序列码粒度。
     *
     * @since 1.1.0
     */
    enum SerialCodeGranularity {
        SETTING, DEVICE
    }

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
         * @param variableId   变量 ID。
         * @param variableType 变量类型。
         * @return 节点变量的值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        Object inspectNodeVariable(@Nonnull String variableId, @Nonnull VariableType variableType) throws Exception;

        /**
         * 插入/更新节点变量。
         *
         * @param variableId   变量 ID。
         * @param variableType 变量类型。
         * @param value        变量值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertNodeVariable(@Nonnull String variableId, @Nonnull VariableType variableType, Object value)
                throws Exception;

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
         * @param variableId   变量 ID。
         * @param variableType 变量类型。
         * @return 公共变量的值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        Object inspectCommonVariable(@Nonnull String variableId, @Nonnull VariableType variableType) throws Exception;

        /**
         * 插入/更新公共变量。
         *
         * @param variableId   变量 ID。
         * @param variableType 变量类型。
         * @param value        变量值。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertCommonVariable(@Nonnull String variableId, @Nonnull VariableType variableType, Object value)
                throws Exception;

        /**
         * 移除公共变量。
         *
         * @param variableId 变量 ID。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeCommonVariable(@Nonnull String variableId) throws Exception;
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
}
