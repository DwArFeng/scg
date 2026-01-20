package com.dwarfeng.scg.node.configuration;

import com.dwarfeng.scg.impl.service.operation.ScgSettingCrudOperation;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.scg.stack.cache.CommonVariableCache;
import com.dwarfeng.scg.stack.cache.GeneratorSupportCache;
import com.dwarfeng.scg.stack.cache.NodeVariableCache;
import com.dwarfeng.scg.stack.dao.CommonVariableDao;
import com.dwarfeng.scg.stack.dao.GeneratorSupportDao;
import com.dwarfeng.scg.stack.dao.NodeVariableDao;
import com.dwarfeng.scg.stack.dao.ScgSettingDao;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    private final GeneratorSupportDao generatorSupportDao;
    private final GeneratorSupportCache generatorSupportCache;
    private final ScgSettingCrudOperation scgSettingCrudOperation;
    private final ScgSettingDao scgSettingDao;
    private final NodeVariableDao nodeVariableDao;
    private final NodeVariableCache nodeVariableCache;
    private final CommonVariableDao commonVariableDao;
    private final CommonVariableCache commonVariableCache;

    @Value("${cache.timeout.entity.generator_support}")
    private long generatorSupportTimeout;
    @Value("${cache.timeout.entity.node_variable}")
    private long nodeVariableTimeout;
    @Value("${cache.timeout.entity.common_variable}")
    private long commonVariableTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GeneratorSupportDao generatorSupportDao, GeneratorSupportCache generatorSupportCache,
            ScgSettingCrudOperation scgSettingCrudOperation, ScgSettingDao scgSettingDao,
            NodeVariableDao nodeVariableDao, NodeVariableCache nodeVariableCache,
            CommonVariableDao commonVariableDao, CommonVariableCache commonVariableCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generatorSupportDao = generatorSupportDao;
        this.generatorSupportCache = generatorSupportCache;
        this.scgSettingCrudOperation = scgSettingCrudOperation;
        this.scgSettingDao = scgSettingDao;
        this.nodeVariableDao = nodeVariableDao;
        this.nodeVariableCache = nodeVariableCache;
        this.commonVariableDao = commonVariableDao;
        this.commonVariableCache = commonVariableCache;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, GeneratorSupport> generatorSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                generatorSupportDao,
                generatorSupportCache,
                new ExceptionKeyGenerator<>(),
                generatorSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<GeneratorSupport> generatorSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                generatorSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<GeneratorSupport> generatorSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                generatorSupportDao
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ScgSetting> scgSettingCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                scgSettingCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ScgSetting> scgSettingDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                scgSettingDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ScgSetting> scgSettingDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                scgSettingDao
        );
    }

    @Bean
    public GeneralBatchCrudService<NodeVariableKey, NodeVariable> nodeVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                nodeVariableDao,
                nodeVariableCache,
                new ExceptionKeyGenerator<>(),
                nodeVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<NodeVariable> nodeVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                nodeVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<NodeVariable> nodeVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                nodeVariableDao
        );
    }

    @Bean
    public GeneralBatchCrudService<CommonVariableKey, CommonVariable> commonVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                commonVariableDao,
                commonVariableCache,
                new ExceptionKeyGenerator<>(),
                commonVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<CommonVariable> commonVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                commonVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<CommonVariable> commonVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                commonVariableDao
        );
    }
}
