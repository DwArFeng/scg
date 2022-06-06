package com.dwarfeng.scg.stack.dao;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 流水码生成设置数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ScgSettingDao extends BatchBaseDao<StringIdKey, ScgSetting>, EntireLookupDao<ScgSetting>,
        PresetLookupDao<ScgSetting> {
}
