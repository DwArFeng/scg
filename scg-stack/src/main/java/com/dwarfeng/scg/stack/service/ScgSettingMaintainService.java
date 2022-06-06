package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 流水码生成设置维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ScgSettingMaintainService extends BatchCrudService<StringIdKey, ScgSetting>,
        EntireLookupService<ScgSetting>, PresetLookupService<ScgSetting> {

    String ENABLED = "enabled";
    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
