package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 流水码生成设置维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface NodeVariableMaintainService extends BatchCrudService<NodeVariableKey, NodeVariable>,
        EntireLookupService<NodeVariable>, PresetLookupService<NodeVariable> {

    String CHILD_FOR_SCG_SETTING = "child_for_scg_setting";
    String SCG_SETTING_ID_LIKE = "scg_setting_id_like";
}
