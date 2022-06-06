package com.dwarfeng.scg.stack.cache;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 流水码生成设置缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ScgSettingCache extends BatchBaseCache<StringIdKey, ScgSetting> {
}
