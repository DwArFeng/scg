package com.dwarfeng.scg.api.integration.subgrade;

import com.dwarfeng.scg.stack.service.GenerateService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.GenerateException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 基于 SCG 实现的 StringIdKey 生成器。
 *
 * @author DwArFeng
 * @since 1.2.1
 */
public class ScgStringIdKeyGenerator implements KeyGenerator<StringIdKey> {

    private final GenerateService generateService;

    private final StringIdKey scgSettingKey;

    public ScgStringIdKeyGenerator(GenerateService generateService, StringIdKey scgSettingKey) {
        this.generateService = generateService;
        this.scgSettingKey = scgSettingKey;
    }

    @Override
    public StringIdKey generate() throws GenerateException {
        try {
            return new StringIdKey(generateService.generate(scgSettingKey));
        } catch (Exception e) {
            throw new GenerateException(e);
        }
    }

    @Override
    public List<StringIdKey> batchGenerate(int size) throws GenerateException {
        try {
            List<String> strings = generateService.generate(scgSettingKey, size);
            return strings.stream().map(StringIdKey::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new GenerateException(e);
        }
    }

    @Override
    public String toString() {
        return "ScgStringIdKeyGenerator{" +
                "generateService=" + generateService +
                ", scgSettingKey=" + scgSettingKey +
                '}';
    }
}
