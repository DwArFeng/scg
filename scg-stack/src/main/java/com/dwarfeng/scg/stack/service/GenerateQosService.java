package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.struct.GeneratorLock;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.List;

/**
 * 生成服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GenerateQosService extends Service {

    /**
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @return 生成的序列码。
     * @throws ServiceException 服务异常。
     */
    String generate(StringIdKey scgSettingKey) throws ServiceException;

    /**
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          生成数量。
     * @return 生成的序列码组成的列表。
     * @throws ServiceException 服务异常。
     */
    List<String> generate(StringIdKey scgSettingKey, int size) throws ServiceException;

    /**
     * 获取指定主键对应的生成器。
     *
     * @param scgSettingKey 指定的主键。
     * @return 指定部件的评估上下文，如果主键对应的生成器信息不存在，则返回 null。
     * @throws ServiceException 服务异常。
     */
    Generator getGenerator(StringIdKey scgSettingKey) throws ServiceException;

    /**
     * 清除生成器本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearGenerateLocalCache() throws ServiceException;

    /**
     * 获取指定流水码生成设置主键对应的设置生成器锁。
     *
     * @param scgSettingKey 指定的流水码生成设置主键。
     * @return 指定的主键对应的设置生成器锁。
     * @throws ServiceException 服务异常。
     */
    GeneratorLock getSettingGeneratorLock(StringIdKey scgSettingKey) throws ServiceException;

    /**
     * 清除设置生成器锁本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearSettingGeneratorLockLocalCache() throws ServiceException;

    /**
     * 获取指定流水码生成设备主键对应的设备生成器锁。
     *
     * @param scgDeviceKey 指定的流水码生成设备主键。
     * @return 指定的主键对应的设备生成器锁。
     * @throws ServiceException 服务异常。
     */
    GeneratorLock getDeviceGeneratorLock(StringIdKey scgDeviceKey) throws ServiceException;

    /**
     * 清除设备生成器锁本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearDeviceGeneratorLockLocalCache() throws ServiceException;
}
