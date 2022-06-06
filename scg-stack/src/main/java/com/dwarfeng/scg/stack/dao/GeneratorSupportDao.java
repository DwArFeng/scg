package com.dwarfeng.scg.stack.dao;

import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 生成器支持数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GeneratorSupportDao extends BatchBaseDao<StringIdKey, GeneratorSupport>,
        EntireLookupDao<GeneratorSupport>, PresetLookupDao<GeneratorSupport> {
}
