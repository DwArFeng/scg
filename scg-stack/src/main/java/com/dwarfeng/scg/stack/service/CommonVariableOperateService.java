package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.dto.CommonVariableInspectInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableRemoveInfo;
import com.dwarfeng.scg.stack.bean.dto.CommonVariableUpsertInfo;
import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 公共变量操作服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface CommonVariableOperateService extends Service {

    /**
     * 查看指定的公共变量。
     *
     * <p>
     * 如果指定的公共变量不存在，则返回 null。
     *
     * @param inspectInfo 查看信息。
     * @return 指定的公共变量。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    CommonVariable inspect(CommonVariableInspectInfo inspectInfo) throws ServiceException;

    /**
     * 向指定的公共变量中插入或更新指定的值。
     *
     * @param upsertInfo 插入更新信息。
     * @throws ServiceException 服务异常。
     */
    void upsert(CommonVariableUpsertInfo upsertInfo) throws ServiceException;

    /**
     * 从指定的公共变量中移除指定的值。
     *
     * <p>
     * 如果指定的公共变量不存在，则什么都不做。
     *
     * @param removeInfo 移除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(CommonVariableRemoveInfo removeInfo) throws ServiceException;
}
