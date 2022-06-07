package com.dwarfeng.scg.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GenerateService extends Service {

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
}
