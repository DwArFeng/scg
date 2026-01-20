package com.dwarfeng.scg.impl.bean;

import com.dwarfeng.scg.impl.bean.entity.HibernateCommonVariable;
import com.dwarfeng.scg.impl.bean.entity.HibernateGeneratorSupport;
import com.dwarfeng.scg.impl.bean.entity.HibernateNodeVariable;
import com.dwarfeng.scg.impl.bean.entity.HibernateScgSetting;
import com.dwarfeng.scg.impl.bean.key.HibernateCommonVariableKey;
import com.dwarfeng.scg.impl.bean.key.HibernateNodeVariableKey;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>impl</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    // -----------------------------------------------------------Scg Key-----------------------------------------------------------
    HibernateCommonVariableKey commonVariableKeyToHibernate(CommonVariableKey commonVariableKey);

    @InheritInverseConfiguration
    CommonVariableKey commonVariableKeyFromHibernate(HibernateCommonVariableKey hibernateCommonVariableKey);

    HibernateNodeVariableKey nodeVariableKeyToHibernate(NodeVariableKey nodeVariableKey);

    @InheritInverseConfiguration
    NodeVariableKey nodeVariableKeyFromHibernate(HibernateNodeVariableKey hibernateNodeVariableKey);

    // -----------------------------------------------------------Scg Entity-----------------------------------------------------------
    @Mapping(target = "variableId", ignore = true)
    @Mapping(target = "scgSettingId", ignore = true)
    @Mapping(target = "scgSetting", ignore = true)
    HibernateCommonVariable commonVariableToHibernate(CommonVariable commonVariable);

    @InheritInverseConfiguration
    CommonVariable commonVariableFromHibernate(HibernateCommonVariable hibernateCommonVariable);

    @Mapping(target = "stringId", ignore = true)
    HibernateGeneratorSupport generatorSupportToHibernate(GeneratorSupport generatorSupport);

    @InheritInverseConfiguration
    GeneratorSupport generatorSupportFromHibernate(HibernateGeneratorSupport hibernateGeneratorSupport);

    @Mapping(target = "variableId", ignore = true)
    @Mapping(target = "scgSettingId", ignore = true)
    @Mapping(target = "scgSetting", ignore = true)
    @Mapping(target = "deviceId", ignore = true)
    HibernateNodeVariable nodeVariableToHibernate(NodeVariable nodeVariable);

    @InheritInverseConfiguration
    NodeVariable nodeVariableFromHibernate(HibernateNodeVariable hibernateNodeVariable);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "nodeVariables", ignore = true)
    @Mapping(target = "commonVariables", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "modifiedDatamark", ignore = true)
    HibernateScgSetting scgSettingToHibernate(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromHibernate(HibernateScgSetting hibernateScgSetting);
}
