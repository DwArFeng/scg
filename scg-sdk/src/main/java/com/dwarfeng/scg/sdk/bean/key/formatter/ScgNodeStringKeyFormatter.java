package com.dwarfeng.scg.sdk.bean.key.formatter;

import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * ScgNodeKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ScgNodeStringKeyFormatter implements StringKeyFormatter<ScgNodeKey> {

    private String prefix;

    public ScgNodeStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(ScgNodeKey key) {
        Objects.requireNonNull(key);
        return String.format("%s%s_%s", prefix, key.getScgSettingId(), key.getDeviceId());
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "ScgNodeStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
