package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

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
     * 基于指定的设置批量生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          批量大小。
     * @return 批量生成的序列码组成的数组。
     * @throws ServiceException 服务异常。
     */
    default List<String> batchGenerate(StringIdKey scgSettingKey, int size) throws ServiceException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(generate(scgSettingKey));
        }
        return result;
    }

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
     * 获取指定主键对应的本地锁。
     *
     * @param scgSettingKey 指定的主键。
     * @return 指定的主键对应的本地锁。
     * @throws ServiceException 服务异常。
     */
    Lock getLocalLock(StringIdKey scgSettingKey) throws ServiceException;

    /**
     * 清除锁本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLockLocalCache() throws ServiceException;
}
