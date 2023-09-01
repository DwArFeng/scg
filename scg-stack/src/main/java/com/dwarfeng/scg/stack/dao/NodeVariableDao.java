package com.dwarfeng.scg.stack.dao;

import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 节点变量数据访问层。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface NodeVariableDao extends BatchBaseDao<NodeVariableKey, NodeVariable>, EntireLookupDao<NodeVariable>,
        PresetLookupDao<NodeVariable> {
}
