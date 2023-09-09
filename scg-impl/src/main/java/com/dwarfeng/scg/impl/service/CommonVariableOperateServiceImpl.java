package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.dto.CommonVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.handler.CommonVariableOperateHandler;
import com.dwarfeng.scg.stack.service.CommonVariableOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class CommonVariableOperateServiceImpl implements CommonVariableOperateService {

    private final CommonVariableOperateHandler commonVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public CommonVariableOperateServiceImpl(
            CommonVariableOperateHandler commonVariableOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.commonVariableOperateHandler = commonVariableOperateHandler;
        this.sem = sem;
    }

    @Nullable
    @Override
    public CommonVariable inspect(CommonVariableInspectInfo inspectInfo) throws ServiceException {
        try {
            return commonVariableOperateHandler.inspect(inspectInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看指定的公共变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsert(CommonVariableUpsertInfo upsertInfo) throws ServiceException {
        try {
            commonVariableOperateHandler.upsert(upsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse(
                    "向指定的公共变量中插入或更新指定的值时发生异常", LogLevel.WARN, e, sem
            );
        }
    }

    @Override
    public void remove(CommonVariableRemoveInfo removeInfo) throws ServiceException {
        try {
            commonVariableOperateHandler.remove(removeInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("从指定的公共变量中移除指定的值时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
