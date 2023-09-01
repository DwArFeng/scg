package com.dwarfeng.scg.stack.dao;

import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 公共变量数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface CommonVariableDao extends BatchBaseDao<CommonVariableKey, CommonVariable>,
        EntireLookupDao<CommonVariable>, PresetLookupDao<CommonVariable> {
}
