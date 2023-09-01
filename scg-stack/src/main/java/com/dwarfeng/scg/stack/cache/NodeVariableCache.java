package com.dwarfeng.scg.stack.cache;

import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 节点变量缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface NodeVariableCache extends BatchBaseCache<NodeVariableKey, NodeVariable> {
}
