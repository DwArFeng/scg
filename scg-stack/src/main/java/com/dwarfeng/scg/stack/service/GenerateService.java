package com.dwarfeng.scg.stack.service;

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
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          生成数量。
     * @return 生成的序列码组成的列表。
     * @throws ServiceException 服务异常。
     */
    List<String> generate(StringIdKey scgSettingKey, int size) throws ServiceException;
}
