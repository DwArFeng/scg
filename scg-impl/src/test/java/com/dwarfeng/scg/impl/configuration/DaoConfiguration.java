package com.dwarfeng.scg.impl.configuration;

import com.dwarfeng.scg.impl.bean.HibernateMapper;
import com.dwarfeng.scg.impl.bean.entity.HibernateGeneratorSupport;
import com.dwarfeng.scg.impl.bean.entity.HibernateScgSetting;
import com.dwarfeng.scg.impl.dao.preset.GeneratorSupportPresetCriteriaMaker;
import com.dwarfeng.scg.impl.dao.preset.ScgNodeInfoPresetEntityFilter;
import com.dwarfeng.scg.impl.dao.preset.ScgSettingPresetCriteriaMaker;
import com.dwarfeng.scg.sdk.bean.FastJsonMapper;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgNodeInfo;
import com.dwarfeng.scg.sdk.bean.key.formatter.ScgNodeStringKeyFormatter;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.*;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate hibernateTemplate;
    private final RedisTemplate<String, ?> redisTemplate;

    private final GeneratorSupportPresetCriteriaMaker generatorSupportPresetCriteriaMaker;
    private final ScgSettingPresetCriteriaMaker scgSettingPresetCriteriaMaker;
    private final ScgNodeInfoPresetEntityFilter scgNodeInfoPresetEntityFilter;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    @Value("${redis.dbkey.scg_node_info}")
    private String scgNodeInfoDbKey;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate, RedisTemplate<String, ?> redisTemplate,
            GeneratorSupportPresetCriteriaMaker generatorSupportPresetCriteriaMaker,
            ScgSettingPresetCriteriaMaker scgSettingPresetCriteriaMaker,
            ScgNodeInfoPresetEntityFilter scgNodeInfoPresetEntityFilter
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.redisTemplate = redisTemplate;
        this.generatorSupportPresetCriteriaMaker = generatorSupportPresetCriteriaMaker;
        this.scgSettingPresetCriteriaMaker = scgSettingPresetCriteriaMaker;
        this.scgNodeInfoPresetEntityFilter = scgNodeInfoPresetEntityFilter;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, GeneratorSupport, HibernateGeneratorSupport>
    generatorSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        GeneratorSupport.class, HibernateGeneratorSupport.class, HibernateMapper.class
                ),
                HibernateGeneratorSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<GeneratorSupport, HibernateGeneratorSupport>
    generatorSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        GeneratorSupport.class, HibernateGeneratorSupport.class, HibernateMapper.class
                ),
                HibernateGeneratorSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<GeneratorSupport, HibernateGeneratorSupport>
    generatorSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        GeneratorSupport.class, HibernateGeneratorSupport.class, HibernateMapper.class
                ),
                HibernateGeneratorSupport.class,
                generatorSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ScgSetting, HibernateScgSetting>
    scgSettingHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, HibernateMapper.class),
                HibernateScgSetting.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ScgSetting, HibernateScgSetting> scgSettingHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, HibernateMapper.class),
                HibernateScgSetting.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ScgSetting, HibernateScgSetting> scgSettingHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, HibernateMapper.class),
                HibernateScgSetting.class,
                scgSettingPresetCriteriaMaker
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseDao<ScgNodeKey, ScgNodeInfo, FastJsonScgNodeInfo> scgNodeInfoRedisBatchBaseDao() {
        return new RedisBatchBaseDao<>(
                (RedisTemplate<String, FastJsonScgNodeInfo>) redisTemplate,
                new ScgNodeStringKeyFormatter("key."),
                new MapStructBeanTransformer<>(ScgNodeInfo.class, FastJsonScgNodeInfo.class, FastJsonMapper.class),
                scgNodeInfoDbKey
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisEntireLookupDao<ScgNodeKey, ScgNodeInfo, FastJsonScgNodeInfo> scgNodeInfoRedisEntireLookupDao() {
        return new RedisEntireLookupDao<>(
                (RedisTemplate<String, FastJsonScgNodeInfo>) redisTemplate,
                new ScgNodeStringKeyFormatter("key."),
                new MapStructBeanTransformer<>(ScgNodeInfo.class, FastJsonScgNodeInfo.class, FastJsonMapper.class),
                scgNodeInfoDbKey
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisPresetLookupDao<ScgNodeKey, ScgNodeInfo, FastJsonScgNodeInfo> scgNodeInfoRedisPresetLookupDao() {
        return new RedisPresetLookupDao<>(
                (RedisTemplate<String, FastJsonScgNodeInfo>) redisTemplate,
                new ScgNodeStringKeyFormatter("key."),
                new MapStructBeanTransformer<>(ScgNodeInfo.class, FastJsonScgNodeInfo.class, FastJsonMapper.class),
                scgNodeInfoPresetEntityFilter,
                scgNodeInfoDbKey
        );
    }
}
