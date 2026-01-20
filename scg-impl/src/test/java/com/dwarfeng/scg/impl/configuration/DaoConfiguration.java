package com.dwarfeng.scg.impl.configuration;

import com.dwarfeng.scg.impl.bean.BeanMapper;
import com.dwarfeng.scg.impl.bean.entity.HibernateCommonVariable;
import com.dwarfeng.scg.impl.bean.entity.HibernateGeneratorSupport;
import com.dwarfeng.scg.impl.bean.entity.HibernateNodeVariable;
import com.dwarfeng.scg.impl.bean.entity.HibernateScgSetting;
import com.dwarfeng.scg.impl.bean.key.HibernateCommonVariableKey;
import com.dwarfeng.scg.impl.bean.key.HibernateNodeVariableKey;
import com.dwarfeng.scg.impl.dao.preset.CommonVariablePresetCriteriaMaker;
import com.dwarfeng.scg.impl.dao.preset.GeneratorSupportPresetCriteriaMaker;
import com.dwarfeng.scg.impl.dao.preset.NodeVariablePresetCriteriaMaker;
import com.dwarfeng.scg.impl.dao.preset.ScgSettingPresetCriteriaMaker;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate hibernateTemplate;

    private final GeneratorSupportPresetCriteriaMaker generatorSupportPresetCriteriaMaker;
    private final ScgSettingPresetCriteriaMaker scgSettingPresetCriteriaMaker;
    private final NodeVariablePresetCriteriaMaker nodeVariablePresetCriteriaMaker;
    private final CommonVariablePresetCriteriaMaker commonVariablePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            GeneratorSupportPresetCriteriaMaker generatorSupportPresetCriteriaMaker,
            ScgSettingPresetCriteriaMaker scgSettingPresetCriteriaMaker,
            NodeVariablePresetCriteriaMaker nodeVariablePresetCriteriaMaker,
            CommonVariablePresetCriteriaMaker commonVariablePresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.generatorSupportPresetCriteriaMaker = generatorSupportPresetCriteriaMaker;
        this.scgSettingPresetCriteriaMaker = scgSettingPresetCriteriaMaker;
        this.nodeVariablePresetCriteriaMaker = nodeVariablePresetCriteriaMaker;
        this.commonVariablePresetCriteriaMaker = commonVariablePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, GeneratorSupport, HibernateGeneratorSupport>
    generatorSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        GeneratorSupport.class, HibernateGeneratorSupport.class, BeanMapper.class
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
                        GeneratorSupport.class, HibernateGeneratorSupport.class, BeanMapper.class
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
                        GeneratorSupport.class, HibernateGeneratorSupport.class, BeanMapper.class
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
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, BeanMapper.class),
                HibernateScgSetting.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ScgSetting, HibernateScgSetting> scgSettingHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, BeanMapper.class),
                HibernateScgSetting.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ScgSetting, HibernateScgSetting> scgSettingHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ScgSetting.class, HibernateScgSetting.class, BeanMapper.class),
                HibernateScgSetting.class,
                scgSettingPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<NodeVariableKey, HibernateNodeVariableKey, NodeVariable, HibernateNodeVariable>
    nodeVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        NodeVariableKey.class, HibernateNodeVariableKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(NodeVariable.class, HibernateNodeVariable.class, BeanMapper.class),
                HibernateNodeVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<NodeVariable, HibernateNodeVariable>
    nodeVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(NodeVariable.class, HibernateNodeVariable.class, BeanMapper.class),
                HibernateNodeVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<NodeVariable, HibernateNodeVariable>
    nodeVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(NodeVariable.class, HibernateNodeVariable.class, BeanMapper.class),
                HibernateNodeVariable.class,
                nodeVariablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<CommonVariableKey, HibernateCommonVariableKey, CommonVariable, HibernateCommonVariable>
    commonVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        CommonVariableKey.class, HibernateCommonVariableKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        CommonVariable.class, HibernateCommonVariable.class, BeanMapper.class
                ),
                HibernateCommonVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<CommonVariable, HibernateCommonVariable>
    commonVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        CommonVariable.class, HibernateCommonVariable.class, BeanMapper.class
                ),
                HibernateCommonVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<CommonVariable, HibernateCommonVariable>
    commonVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        CommonVariable.class, HibernateCommonVariable.class, BeanMapper.class
                ),
                HibernateCommonVariable.class,
                commonVariablePresetCriteriaMaker
        );
    }
}
