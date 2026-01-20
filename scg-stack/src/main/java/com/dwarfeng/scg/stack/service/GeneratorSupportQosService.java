package com.dwarfeng.scg.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 生成器支持 QoS 服务。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public interface GeneratorSupportQosService extends Service {

    /**
     * 重置生成器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
