package com.dwarfeng.scg.impl.bean;

import com.dwarfeng.scg.impl.bean.entity.HibernateGeneratorSupport;
import com.dwarfeng.scg.impl.bean.entity.HibernateScgSetting;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    @Mapping(target = "stringId", ignore = true)
    HibernateGeneratorSupport generatorSupportToHibernate(GeneratorSupport generatorSupport);

    @InheritInverseConfiguration
    GeneratorSupport generatorSupportFromHibernate(HibernateGeneratorSupport hibernateGeneratorSupport);

    @Mapping(target = "stringId", ignore = true)
    HibernateScgSetting scgSettingToHibernate(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromHibernate(HibernateScgSetting hibernateScgSetting);
}
