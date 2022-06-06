package com.dwarfeng.scg.stack.cache;

import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 生成器支持缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GeneratorSupportCache extends BatchBaseCache<StringIdKey, GeneratorSupport> {
}
