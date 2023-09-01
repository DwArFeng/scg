package com.dwarfeng.scg.impl.bean;

import com.dwarfeng.scg.impl.bean.entity.HibernateGeneratorSupport;
import com.dwarfeng.scg.impl.bean.entity.HibernateNodeVariable;
import com.dwarfeng.scg.impl.bean.entity.HibernateScgSetting;
import com.dwarfeng.scg.impl.bean.key.HibernateNodeVariableKey;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
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

    HibernateNodeVariableKey nodeVariableKeyToHibernate(NodeVariableKey nodeVariableKey);

    @InheritInverseConfiguration
    NodeVariableKey nodeVariableKeyFromHibernate(HibernateNodeVariableKey hibernateNodeVariableKey);

    @Mapping(target = "stringId", ignore = true)
    HibernateGeneratorSupport generatorSupportToHibernate(GeneratorSupport generatorSupport);

    @InheritInverseConfiguration
    GeneratorSupport generatorSupportFromHibernate(HibernateGeneratorSupport hibernateGeneratorSupport);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "nodeVariables", ignore = true)
    HibernateScgSetting scgSettingToHibernate(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromHibernate(HibernateScgSetting hibernateScgSetting);

    @Mapping(target = "variableId", ignore = true)
    @Mapping(target = "scgSettingId", ignore = true)
    @Mapping(target = "scgSetting", ignore = true)
    @Mapping(target = "deviceId", ignore = true)
    HibernateNodeVariable nodeVariableToHibernate(NodeVariable nodeVariable);

    @InheritInverseConfiguration
    NodeVariable nodeVariableFromHibernate(HibernateNodeVariable hibernateNodeVariable);
}
