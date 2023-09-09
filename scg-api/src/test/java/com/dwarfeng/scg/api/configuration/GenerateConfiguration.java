package com.dwarfeng.scg.api.configuration;

import com.dwarfeng.scg.api.integration.subgrade.ScgStringGenerator;
import com.dwarfeng.scg.api.integration.subgrade.ScgStringIdKeyGenerator;
import com.dwarfeng.scg.stack.service.GenerateService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenerateConfiguration {

    private final GenerateService generateService;

    // test.scg_setting_id
    @Value("#{new com.dwarfeng.subgrade.stack.bean.key.StringIdKey('${test.scg_setting_id}')}")
    private StringIdKey scgSettingKey;

    public GenerateConfiguration(GenerateService generateService) {
        this.generateService = generateService;
    }

    @Bean
    public ScgStringGenerator scgStringGenerator() {
        return new ScgStringGenerator(generateService, scgSettingKey);
    }

    @Bean
    public ScgStringIdKeyGenerator scgStringIdKeyGenerator() {
        return new ScgStringIdKeyGenerator(generateService, scgSettingKey);
    }
}
