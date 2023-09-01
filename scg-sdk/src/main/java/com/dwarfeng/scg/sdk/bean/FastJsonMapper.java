package com.dwarfeng.scg.sdk.bean;

import com.dwarfeng.scg.sdk.bean.entity.*;
import com.dwarfeng.scg.sdk.bean.key.FastJsonCommonVariableKey;
import com.dwarfeng.scg.sdk.bean.key.FastJsonNodeVariableKey;
import com.dwarfeng.scg.stack.bean.entity.*;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonNodeVariableKey nodeVariableKeyToFastJson(NodeVariableKey nodeVariableKey);

    @InheritInverseConfiguration
    NodeVariableKey nodeVariableKeyFromFastJson(FastJsonNodeVariableKey fastJsonNodeVariableKey);

    FastJsonCommonVariableKey commonVariableKeyToFastJson(CommonVariableKey commonVariableKey);

    @InheritInverseConfiguration
    CommonVariableKey commonVariableKeyFromFastJson(FastJsonCommonVariableKey fastJsonCommonVariableKey);

    FastJsonGeneratorSupport generatorSupportToFastJson(GeneratorSupport generatorSupport);

    @InheritInverseConfiguration
    GeneratorSupport generatorSupportFromFastJson(FastJsonGeneratorSupport fastJsonGeneratorSupport);

    FastJsonScgNodeInfo scgNodeInfoToFastJson(ScgNodeInfo scgNodeInfo);

    @InheritInverseConfiguration
    ScgNodeInfo scgNodeInfoFromFastJson(FastJsonScgNodeInfo fastJsonScgNodeInfo);

    FastJsonScgSetting scgSettingToFastJson(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromFastJson(FastJsonScgSetting fastJsonScgSetting);

    FastJsonNodeVariable nodeVariableToFastJson(NodeVariable nodeVariable);

    @InheritInverseConfiguration
    NodeVariable nodeVariableFromFastJson(FastJsonNodeVariable fastJsonNodeVariable);

    FastJsonCommonVariable commonVariableToFastJson(CommonVariable commonVariable);

    @InheritInverseConfiguration
    CommonVariable commonVariableFromFastJson(FastJsonCommonVariable fastJsonCommonVariable);
}
