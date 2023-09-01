package com.dwarfeng.scg.stack.cache;

import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 公共变量缓存。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface CommonVariableCache extends BatchBaseCache<CommonVariableKey, CommonVariable> {
}
