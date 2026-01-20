package com.dwarfeng.scg.sdk.bean.key.formatter;

import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * CommonVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class CommonVariableStringKeyFormatter implements StringKeyFormatter<CommonVariableKey> {

    private String prefix;

    public CommonVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(CommonVariableKey key) {
        Objects.requireNonNull(key);
        return String.format("%s%s_%s", prefix, key.getScgSettingId(), key.getVariableId());
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
        return "CommonVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
