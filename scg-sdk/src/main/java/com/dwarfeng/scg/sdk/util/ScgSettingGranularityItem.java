package com.dwarfeng.scg.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 序列码设置粒度条目。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ScgSettingGranularityItem {
}
