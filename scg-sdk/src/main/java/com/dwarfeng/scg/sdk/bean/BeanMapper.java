package com.dwarfeng.scg.sdk.bean;

import com.dwarfeng.scg.sdk.bean.dto.*;
import com.dwarfeng.scg.sdk.bean.entity.*;
import com.dwarfeng.scg.sdk.bean.key.FastJsonCommonVariableKey;
import com.dwarfeng.scg.sdk.bean.key.FastJsonNodeVariableKey;
import com.dwarfeng.scg.sdk.bean.key.FastJsonScgNodeKey;
import com.dwarfeng.scg.stack.bean.dto.*;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Scg Key-----------------------------------------------------------
    FastJsonCommonVariableKey commonVariableKeyToFastJson(CommonVariableKey commonVariableKey);

    @InheritInverseConfiguration
    CommonVariableKey commonVariableKeyFromFastJson(FastJsonCommonVariableKey fastJsonCommonVariableKey);

    FastJsonNodeVariableKey nodeVariableKeyToFastJson(NodeVariableKey nodeVariableKey);

    @InheritInverseConfiguration
    NodeVariableKey nodeVariableKeyFromFastJson(FastJsonNodeVariableKey fastJsonNodeVariableKey);

    FastJsonScgNodeKey scgNodeKeyToFastJson(ScgNodeKey scgNodeKey);

    @InheritInverseConfiguration
    ScgNodeKey scgNodeKeyFromFastJson(FastJsonScgNodeKey fastJsonScgNodeKey);

    // -----------------------------------------------------------Scg Entity-----------------------------------------------------------
    FastJsonCommonVariable commonVariableToFastJson(CommonVariable commonVariable);

    @InheritInverseConfiguration
    CommonVariable commonVariableFromFastJson(FastJsonCommonVariable fastJsonCommonVariable);

    FastJsonGeneratorSupport generatorSupportToFastJson(GeneratorSupport generatorSupport);

    @InheritInverseConfiguration
    GeneratorSupport generatorSupportFromFastJson(FastJsonGeneratorSupport fastJsonGeneratorSupport);

    FastJsonNodeVariable nodeVariableToFastJson(NodeVariable nodeVariable);

    @InheritInverseConfiguration
    NodeVariable nodeVariableFromFastJson(FastJsonNodeVariable fastJsonNodeVariable);

    FastJsonScgSetting scgSettingToFastJson(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromFastJson(FastJsonScgSetting fastJsonScgSetting);

    JSFixedFastJsonCommonVariable commonVariableToJSFixedFastJson(CommonVariable commonVariable);

    @InheritInverseConfiguration
    CommonVariable commonVariableFromJSFixedFastJson(JSFixedFastJsonCommonVariable jSFixedFastJsonCommonVariable);

    JSFixedFastJsonNodeVariable nodeVariableToJSFixedFastJson(NodeVariable nodeVariable);

    @InheritInverseConfiguration
    NodeVariable nodeVariableFromJSFixedFastJson(JSFixedFastJsonNodeVariable jSFixedFastJsonNodeVariable);

    WebInputScgSetting scgSettingToWebInput(ScgSetting scgSetting);

    @InheritInverseConfiguration
    ScgSetting scgSettingFromWebInput(WebInputScgSetting webInputScgSetting);

    // -----------------------------------------------------------Scg DTO-----------------------------------------------------------
    WebInputBatchGenerateInfo batchGenerateInfoToWebInput(BatchGenerateInfo batchGenerateInfo);

    @InheritInverseConfiguration
    BatchGenerateInfo batchGenerateInfoFromWebInput(WebInputBatchGenerateInfo webInputBatchGenerateInfo);

    WebInputCommonVariableInspectInfo commonVariableInspectInfoToWebInput(
            CommonVariableInspectInfo commonVariableInspectInfo
    );

    @InheritInverseConfiguration
    CommonVariableInspectInfo commonVariableInspectInfoFromWebInput(
            WebInputCommonVariableInspectInfo webInputCommonVariableInspectInfo
    );

    WebInputCommonVariableRemoveInfo commonVariableRemoveInfoToWebInput(
            CommonVariableRemoveInfo commonVariableRemoveInfo
    );

    @InheritInverseConfiguration
    CommonVariableRemoveInfo commonVariableRemoveInfoFromWebInput(
            WebInputCommonVariableRemoveInfo webInputCommonVariableRemoveInfo
    );

    WebInputCommonVariableUpsertInfo commonVariableUpsertInfoToWebInput(
            CommonVariableUpsertInfo commonVariableUpsertInfo
    );

    @InheritInverseConfiguration
    CommonVariableUpsertInfo commonVariableUpsertInfoFromWebInput(
            WebInputCommonVariableUpsertInfo webInputCommonVariableUpsertInfo
    );

    WebInputGenerateInfo generateInfoToWebInput(GenerateInfo generateInfo);

    @InheritInverseConfiguration
    GenerateInfo generateInfoFromWebInput(WebInputGenerateInfo webInputGenerateInfo);

    WebInputNodeVariableInspectInfo nodeVariableInspectInfoToWebInput(NodeVariableInspectInfo nodeVariableInspectInfo);

    @InheritInverseConfiguration
    NodeVariableInspectInfo nodeVariableInspectInfoFromWebInput(
            WebInputNodeVariableInspectInfo webInputNodeVariableInspectInfo
    );

    WebInputNodeVariableRemoveInfo nodeVariableRemoveInfoToWebInput(NodeVariableRemoveInfo nodeVariableRemoveInfo);

    @InheritInverseConfiguration
    NodeVariableRemoveInfo nodeVariableRemoveInfoFromWebInput(
            WebInputNodeVariableRemoveInfo webInputNodeVariableRemoveInfo
    );

    WebInputNodeVariableUpsertInfo nodeVariableUpsertInfoToWebInput(NodeVariableUpsertInfo nodeVariableUpsertInfo);

    @InheritInverseConfiguration
    NodeVariableUpsertInfo nodeVariableUpsertInfoFromWebInput(
            WebInputNodeVariableUpsertInfo webInputNodeVariableUpsertInfo
    );
}
