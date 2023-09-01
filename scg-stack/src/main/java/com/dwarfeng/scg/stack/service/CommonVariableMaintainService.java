package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 流水码生成设置维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface CommonVariableMaintainService extends BatchCrudService<CommonVariableKey, CommonVariable>,
        EntireLookupService<CommonVariable>, PresetLookupService<CommonVariable> {

    String CHILD_FOR_SCG_SETTING = "child_for_scg_setting";
    String SCG_SETTING_ID_LIKE = "scg_setting_id_like";
}
