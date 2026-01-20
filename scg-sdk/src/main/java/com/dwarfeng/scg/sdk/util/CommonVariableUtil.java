package com.dwarfeng.scg.sdk.util;

import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;

import java.util.Date;

/**
 * 公共变量工具类。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public final class CommonVariableUtil {

    /**
     * 生成一个值为 <code>null</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, null, null, null, null, null);
    }

    /**
     * 生成一个值为 <code>stringValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param stringValue  指定的字符串值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, String stringValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, stringValue, null, null, null, null, null);
    }

    /**
     * 生成一个值为 <code>booleanValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param booleanValue 指定的布尔值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, Boolean booleanValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, booleanValue, null, null, null, null);
    }

    /**
     * 生成一个值为 <code>integerValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param integerValue 指定的整数值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, Integer integerValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, null, integerValue, null, null, null);
    }

    /**
     * 生成一个值为 <code>longValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param longValue    指定的长整数值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, Long longValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, null, null, longValue, null, null);
    }

    /**
     * 生成一个值为 <code>doubleValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param doubleValue  指定的双精度浮点数值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, Double doubleValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, null, null, null, doubleValue, null);
    }

    /**
     * 生成一个值为 <code>dateValue</code> 的公共变量插入更新信息。
     *
     * @param scgSettingId 流水码生成设置 ID。
     * @param variableId   指定的变量 ID。
     * @param dateValue    指定的日期值。
     * @return 生成的公共变量插入更新信息。
     */
    public static CommonVariableUpsertInfo ofUpsertInfo(String scgSettingId, String variableId, Date dateValue) {
        return new CommonVariableUpsertInfo(scgSettingId, variableId, null, null, null, null, null, dateValue);
    }

    private CommonVariableUtil() {
        throw new IllegalStateException("禁止实例化");
    }
}
