package com.dwarfeng.scg.sdk.bean;

import com.dwarfeng.scg.sdk.bean.entity.FastJsonGeneratorSupport;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonNodeVariable;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgNodeInfo;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgSetting;
import com.dwarfeng.scg.sdk.bean.key.FastJsonNodeVariableKey;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
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
}
