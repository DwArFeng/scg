package com.dwarfeng.scg.sdk.bean.key.formatter;

import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * NodeVariableKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public class NodeVariableStringKeyFormatter implements StringKeyFormatter<NodeVariableKey> {

    private String prefix;

    public NodeVariableStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(NodeVariableKey key) {
        Objects.requireNonNull(key);
        return String.format("%s%s_%s_%s", prefix, key.getScgSettingId(), key.getDeviceId(), key.getVariableId());
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
        return "NodeVariableStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
