package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GenerateHandler extends Handler {

    /**
     * 基于指定的设置生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @return 生成的序列码。
     * @throws HandlerException 处理器异常。
     */
    String generate(StringIdKey scgSettingKey) throws HandlerException;

    /**
     * 基于指定的设置批量生成序列码。
     *
     * @param scgSettingKey 指定的设置。
     * @param size          批量大小。
     * @return 批量生成的序列码组成的数组。
     * @throws HandlerException 处理器异常。
     */
    default List<String> batchGenerate(StringIdKey scgSettingKey, int size) throws HandlerException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(generate(scgSettingKey));
        }
        return result;
    }
}
