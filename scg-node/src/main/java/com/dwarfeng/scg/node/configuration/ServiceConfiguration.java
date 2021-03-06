package com.dwarfeng.scg.node.configuration;

import com.dwarfeng.scg.impl.service.operation.ScgSettingCrudOperation;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.scg.stack.cache.GeneratorSupportCache;
import com.dwarfeng.scg.stack.dao.GeneratorSupportDao;
import com.dwarfeng.scg.stack.dao.ScgNodeInfoDao;
import com.dwarfeng.scg.stack.dao.ScgSettingDao;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.*;
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
    private final ScgNodeInfoDao scgNodeInfoDao;

    @Value("${cache.timeout.entity.generator_support}")
    private long generatorSupportTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GeneratorSupportDao generatorSupportDao, GeneratorSupportCache generatorSupportCache,
            ScgSettingCrudOperation scgSettingCrudOperation, ScgSettingDao scgSettingDao,
            ScgNodeInfoDao scgNodeInfoDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generatorSupportDao = generatorSupportDao;
        this.generatorSupportCache = generatorSupportCache;
        this.scgSettingCrudOperation = scgSettingCrudOperation;
        this.scgSettingDao = scgSettingDao;
        this.scgNodeInfoDao = scgNodeInfoDao;
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, GeneratorSupport> generatorSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                generatorSupportDao,
                generatorSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                generatorSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<GeneratorSupport> generatorSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                generatorSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<GeneratorSupport> generatorSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                generatorSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ScgSetting> scgSettingCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                scgSettingCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ScgSetting> scgSettingDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                scgSettingDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ScgSetting> scgSettingDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                scgSettingDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyBatchCrudService<ScgNodeKey, ScgNodeInfo> scgNodeInfoCustomCrudService() {
        return new DaoOnlyBatchCrudService<>(
                scgNodeInfoDao,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ScgNodeInfo> scgNodeInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                scgNodeInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ScgNodeInfo> scgNodeInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                scgNodeInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
