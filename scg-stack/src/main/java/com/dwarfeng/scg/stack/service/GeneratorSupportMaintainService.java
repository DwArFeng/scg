package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 生成器支持维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GeneratorSupportMaintainService extends BatchCrudService<StringIdKey, GeneratorSupport>,
        EntireLookupService<GeneratorSupport>, PresetLookupService<GeneratorSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置生成器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
