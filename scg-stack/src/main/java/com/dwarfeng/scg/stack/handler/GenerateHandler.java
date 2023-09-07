package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler.GenerateContext;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 生成处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GenerateHandler extends Handler {

    /**
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @return 生成的序列码。
     * @throws HandlerException 处理器异常。
     */
    String generate(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          生成数量。
     * @return 生成的序列码组成的列表。
     * @throws HandlerException 处理器异常。
     */
    List<String> generate(StringIdKey scgSettingKey, int size) throws HandlerException;

    /**
     * 获取指定主键对应的生成上下文。
     *
     * @param scgSettingKey 指定的主键。
     * @return 指定主键对应的生成上下文，如果主键对应的生成器信息不存在，则返回 null。
     * @throws HandlerException 处理器异常。
     */
    GenerateContext getGenerateContext(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 清除生成器本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearGenerateLocalCache() throws HandlerException;

    /**
     * 获取指定流水码生成设置主键对应的设置生成器锁。
     *
     * @param scgSettingKey 指定的流水码生成设置主键。
     * @return 指定的主键对应的设置生成器锁。
     * @throws HandlerException 处理器异常。
     */
    GeneratorLock getSettingGeneratorLock(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 清除设置生成器锁本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearSettingGeneratorLockLocalCache() throws HandlerException;

    /**
     * 获取指定流水码生成设备主键对应的设备生成器锁。
     *
     * @param scgDeviceKey 指定的流水码生成设备主键。
     * @return 指定的主键对应的设备生成器锁。
     * @throws HandlerException 处理器异常。
     */
    GeneratorLock getDeviceGeneratorLock(StringIdKey scgDeviceKey) throws HandlerException;

    /**
     * 清除设备生成器锁本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearDeviceGeneratorLockLocalCache() throws HandlerException;
}
