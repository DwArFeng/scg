package com.dwarfeng.scg.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.2.0
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    /**
     * 序列码生成设置粒度粒度：设置。
     *
     * <p>
     * 生成器生成的序列码在同一个设置下是唯一的，在相同状态下，不同的设备生成的序列码是相同的。
     * 如果设备 ID 不参与序列码的生成，则生成的序列码则有可能是该粒度。<br>
     * 该粒度的序列码生成时，会在序列码生成设置粒度级别下的分布式锁中完成，因此在一个集群中，至多只有一个生成方法在执行。<br>
     * 此粒度下，生成器访问节点变量和公共变量均被允许。<br>
     * 此粒度下，流水码的生成器效率较低，适合生顺序敏感且频率不高的序列码。<br>
     * 典型的序列码格式：1992-12-18-000123(年-月-日-当日序号)。
     */
    @ScgSettingGranularityItem
    public static final int SCG_SETTING_GRANULARITY_SETTING = 0;

    /**
     * 序列码生成设置粒度粒度：设置。
     *
     * <p>
     * 生成器生成的序列码在同一个设备下是唯一的，在相同状态下，不同的设备生成的序列码是不同的。
     * 如果设备 ID 参与序列码的生成，则生成的序列码则一定是该粒度。<br>
     * 该粒度的序列码生成时，会在设备级别下的本地锁中完成，因此在一个集群中，每个设备可同时执行生成方法。<br>
     * 此粒度下，生成器允许访问节点变量，但不允许访问公共变量。<br>
     * 此粒度下，流水码的生成器效率较高，适合生顺序不敏感且频率较高的序列码。<br>
     * 典型的序列码格式：1992-12-18-01-000123(年-月-日-设备ID-当日序号)。
     */
    @ScgSettingGranularityItem
    public static final int SCG_SETTING_GRANULARITY_DEVICE = 1;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> scgSettingGranularitySpace = null;

    /**
     * 获取序列码生成设置粒度的空间。
     *
     * @return 序列码生成设置粒度的空间。
     */
    public static List<Integer> scgSettingGranularitySpace() {
        if (Objects.nonNull(scgSettingGranularitySpace)) {
            return scgSettingGranularitySpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(scgSettingGranularitySpace)) {
                return scgSettingGranularitySpace;
            }
            initScgSettingGranularitySpace();
            return scgSettingGranularitySpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initScgSettingGranularitySpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(ScgSettingGranularityItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        scgSettingGranularitySpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
