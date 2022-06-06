package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 流水码生成设置维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ScgNodeInfoMaintainService extends BatchCrudService<ScgNodeKey, ScgNodeInfo>,
        EntireLookupService<ScgNodeInfo>, PresetLookupService<ScgNodeInfo> {

    String SCG_SETTING_ID_EQUALS = "scg_setting_id_equals";
}
