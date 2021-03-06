package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

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
     * 基于指定的设置批量生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          批量大小。
     * @return 批量生成的序列码组成的数组。
     * @throws HandlerException 处理器异常。
     */
    default List<String> batchGenerate(StringIdKey scgSettingKey, int size) throws HandlerException {
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
     * @throws HandlerException 处理器异常。
     */
    Generator getGenerator(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 清除生成器本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearGenerateLocalCache() throws HandlerException;

    /**
     * 获取指定主键对应的本地锁。
     *
     * @param scgSettingKey 指定的主键。
     * @return 指定的主键对应的本地锁。
     * @throws HandlerException 处理器异常。
     */
    Lock getLocalLock(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 清除锁本地缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearLockLocalCache() throws HandlerException;
}
