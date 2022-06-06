package com.dwarfeng.scg.stack.dao;

import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 流水码生成节点信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ScgNodeInfoDao extends BatchBaseDao<ScgNodeKey, ScgNodeInfo>, EntireLookupDao<ScgNodeInfo>,
        PresetLookupDao<ScgNodeInfo> {
}
