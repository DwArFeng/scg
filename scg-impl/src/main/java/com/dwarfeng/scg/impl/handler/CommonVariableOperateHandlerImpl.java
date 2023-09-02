package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.dto.CommonVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.handler.CommonVariableOperateHandler;
import com.dwarfeng.scg.stack.service.CommonVariableMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;

@Component
public class CommonVariableOperateHandlerImpl implements CommonVariableOperateHandler {

    private final CommonVariableMaintainService commonVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public CommonVariableOperateHandlerImpl(
            CommonVariableMaintainService commonVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.commonVariableMaintainService = commonVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public CommonVariable inspect(CommonVariableInspectInfo inspectInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = inspectInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            String variableId = inspectInfo.getVariableId();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 CommonVariableKey。
            CommonVariableKey commonVariableKey = new CommonVariableKey(scgSettingId, variableId);

            // 查询并返回公共变量。
            return commonVariableMaintainService.getIfExists(commonVariableKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void upsert(CommonVariableUpsertInfo upsertInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = upsertInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            String variableId = upsertInfo.getVariableId();
            String stringValue = upsertInfo.getStringValue();
            Boolean booleanValue = upsertInfo.getBooleanValue();
            Integer integerValue = upsertInfo.getIntegerValue();
            Long longValue = upsertInfo.getLongValue();
            Double doubleValue = upsertInfo.getDoubleValue();
            Date dateValue = upsertInfo.getDateValue();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 CommonVariable。
            CommonVariableKey commonVariableKey = new CommonVariableKey(scgSettingId, variableId);
            CommonVariable commonVariable = new CommonVariable(
                    commonVariableKey, stringValue, booleanValue, integerValue, longValue, doubleValue, dateValue,
                    new Date()
            );

            // 插入或更新公共变量。
            commonVariableMaintainService.insertOrUpdate(commonVariable);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void remove(CommonVariableRemoveInfo removeInfo) throws HandlerException {
        try {
            // 展开参数。
            String scgSettingId = removeInfo.getScgSettingId();
            StringIdKey scgSettingKey = new StringIdKey(scgSettingId);
            String variableId = removeInfo.getVariableId();

            // 确认流水码生成设置存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);

            // 构造 CommonVariableKey。
            CommonVariableKey commonVariableKey = new CommonVariableKey(scgSettingId, variableId);

            // 删除公共变量。
            commonVariableMaintainService.deleteIfExists(commonVariableKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }
}
