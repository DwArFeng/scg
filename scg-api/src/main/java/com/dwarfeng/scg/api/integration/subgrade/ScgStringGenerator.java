package com.dwarfeng.scg.api.integration.subgrade;

import com.dwarfeng.scg.stack.service.GenerateService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.GenerateException;
import com.dwarfeng.subgrade.stack.generation.Generator;

import java.util.List;

/**
 * 基于 SCG 实现的 String 生成器。
 *
 * @author DwArFeng
 * @since 1.2.1
 */
public class ScgStringGenerator implements Generator<String> {

    private final GenerateService generateService;

    private final StringIdKey scgSettingKey;

    public ScgStringGenerator(GenerateService generateService, StringIdKey scgSettingKey) {
        this.generateService = generateService;
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public String generate() throws GenerateException {
        try {
            return generateService.generate(scgSettingKey);
        } catch (Exception e) {
            throw new GenerateException(e);
        }
    }

    @Override
    public List<String> batchGenerate(int size) throws GenerateException {
        try {
            return generateService.generate(scgSettingKey, size);
        } catch (Exception e) {
            throw new GenerateException(e);
        }
    }

    @Override
    public String toString() {
        return "ScgStringGenerator{" +
                "generateService=" + generateService +
                ", scgSettingKey=" + scgSettingKey +
                '}';
    }
}
