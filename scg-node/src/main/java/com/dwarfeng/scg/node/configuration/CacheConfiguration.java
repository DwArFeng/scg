package com.dwarfeng.scg.node.configuration;

import com.dwarfeng.scg.sdk.bean.FastJsonMapper;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonGeneratorSupport;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgSetting;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.generator_support}")
    private String generatorSupportPrefix;
    @Value("${cache.prefix.entity.scg_setting}")
    private String scgSettingPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, GeneratorSupport, FastJsonGeneratorSupport>
    generatorSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonGeneratorSupport>) template,
                new StringIdStringKeyFormatter(generatorSupportPrefix),
                new MapStructBeanTransformer<>(
                        GeneratorSupport.class, FastJsonGeneratorSupport.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ScgSetting, FastJsonScgSetting> scgSettingRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonScgSetting>) template,
                new StringIdStringKeyFormatter(scgSettingPrefix),
                new MapStructBeanTransformer<>(ScgSetting.class, FastJsonScgSetting.class, FastJsonMapper.class)
        );
    }
}
