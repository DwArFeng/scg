package com.dwarfeng.scg.sdk.util;

import com.dwarfeng.scg.stack.bean.dto.NodeVariableUpsertInfo;

import java.util.Date;

/**
 * 节点变量工具类。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public final class NodeVariableUtil {

    /**
     * 生成一个值为 <code>null</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(String scgSettingId, Integer deviceId, String variableId) {
        return new NodeVariableUpsertInfo(scgSettingId, deviceId, variableId, null, null, null, null, null, null);
    }

    /**
     * 生成一个值为 <code>stringValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param stringValue  指定的字符串值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, String stringValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, stringValue, null, null, null, null, null
        );
    }

    /**
     * 生成一个值为 <code>booleanValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param booleanValue 指定的布尔值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, Boolean booleanValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, null, booleanValue, null, null, null, null
        );
    }

    /**
     * 生成一个值为 <code>integerValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param integerValue 指定的整数值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, Integer integerValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, null, null, integerValue, null, null, null
        );
    }

    /**
     * 生成一个值为 <code>longValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param longValue    指定的长整数值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, Long longValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, null, null, null, longValue, null, null
        );
    }

    /**
     * 生成一个值为 <code>doubleValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param doubleValue  指定的双精度浮点数值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, Double doubleValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, null, null, null, null, doubleValue, null
        );
    }

    /**
     * 生成一个值为 <code>dateValue</code> 的节点变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param dateValue    指定的日期值。
     * @return 生成的节点变量插入更新信息。
     */
    public static NodeVariableUpsertInfo ofUpsertInfo(
            String scgSettingId, Integer deviceId, String variableId, Date dateValue
    ) {
        return new NodeVariableUpsertInfo(
                scgSettingId, deviceId, variableId, null, null, null, null, null, dateValue
        );
    }

    private NodeVariableUtil() {
        throw new IllegalStateException("禁止实例化");
    }
}
