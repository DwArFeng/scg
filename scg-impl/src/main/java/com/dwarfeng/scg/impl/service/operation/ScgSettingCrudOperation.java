package com.dwarfeng.scg.impl.service.operation;

import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.scg.stack.cache.CommonVariableCache;
import com.dwarfeng.scg.stack.cache.NodeVariableCache;
import com.dwarfeng.scg.stack.cache.ScgSettingCache;
import com.dwarfeng.scg.stack.dao.CommonVariableDao;
import com.dwarfeng.scg.stack.dao.NodeVariableDao;
import com.dwarfeng.scg.stack.dao.ScgSettingDao;
import com.dwarfeng.scg.stack.service.CommonVariableMaintainService;
import com.dwarfeng.scg.stack.service.NodeVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScgSettingCrudOperation implements BatchCrudOperation<StringIdKey, ScgSetting> {

    private final ScgSettingDao scgSettingDao;
    private final ScgSettingCache scgSettingCache;

    private final NodeVariableDao nodeVariableDao;
    private final NodeVariableCache nodeVariableCache;

    private final CommonVariableDao commonVariableDao;
    private final CommonVariableCache commonVariableCache;

    @Value("${cache.timeout.entity.scg_setting}")
    private long scgSettingTimeout;

    public ScgSettingCrudOperation(
            ScgSettingDao scgSettingDao, ScgSettingCache scgSettingCache,
            NodeVariableDao nodeVariableDao, NodeVariableCache nodeVariableCache,
            CommonVariableDao commonVariableDao, CommonVariableCache commonVariableCache
    ) {
        this.scgSettingDao = scgSettingDao;
        this.scgSettingCache = scgSettingCache;
        this.nodeVariableDao = nodeVariableDao;
        this.nodeVariableCache = nodeVariableCache;
        this.commonVariableDao = commonVariableDao;
        this.commonVariableCache = commonVariableCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return scgSettingCache.exists(key) || scgSettingDao.exists(key);
    }

    @Override
    public ScgSetting get(StringIdKey key) throws Exception {
        if (scgSettingCache.exists(key)) {
            return scgSettingCache.get(key);
        } else {
            if (!scgSettingDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ScgSetting scgSetting = scgSettingDao.get(key);
            scgSettingCache.push(scgSetting, scgSettingTimeout);
            return scgSetting;
        }
    }

    @Override
    public StringIdKey insert(ScgSetting scgSetting) throws Exception {
        scgSettingCache.push(scgSetting, scgSettingTimeout);
        return scgSettingDao.insert(scgSetting);
    }

    @Override
    public void update(ScgSetting scgSetting) throws Exception {
        scgSettingCache.push(scgSetting, scgSettingTimeout);
        scgSettingDao.update(scgSetting);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与流水码生成设置相关的节点变量。
        List<NodeVariableKey> nodeVariableKeys = nodeVariableDao.lookup(
                NodeVariableMaintainService.CHILD_FOR_SCG_SETTING, new Object[]{key}
        ).stream().map(NodeVariable::getKey).collect(Collectors.toList());
        nodeVariableCache.batchDelete(nodeVariableKeys);
        nodeVariableDao.batchDelete(nodeVariableKeys);

        // 删除与流水码生成设置相关的公共变量。
        List<CommonVariableKey> commonVariableKeys = commonVariableDao.lookup(
                CommonVariableMaintainService.CHILD_FOR_SCG_SETTING, new Object[]{key}
        ).stream().map(CommonVariable::getKey).collect(Collectors.toList());
        commonVariableCache.batchDelete(commonVariableKeys);
        commonVariableDao.batchDelete(commonVariableKeys);

        // 删除流水码生成设置实体本身。
        scgSettingCache.delete(key);
        scgSettingDao.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return scgSettingCache.allExists(keys) || scgSettingDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return scgSettingCache.nonExists(keys) && scgSettingCache.nonExists(keys);
    }

    @Override
    public List<ScgSetting> batchGet(List<StringIdKey> keys) throws Exception {
        if (scgSettingCache.allExists(keys)) {
            return scgSettingCache.batchGet(keys);
        } else {
            if (!scgSettingDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ScgSetting> scgSettings = scgSettingDao.batchGet(keys);
            scgSettingCache.batchPush(scgSettings, scgSettingTimeout);
            return scgSettings;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<ScgSetting> scgSettings) throws Exception {
        scgSettingCache.batchPush(scgSettings, scgSettingTimeout);
        return scgSettingDao.batchInsert(scgSettings);
    }

    @Override
    public void batchUpdate(List<ScgSetting> scgSettings) throws Exception {
        scgSettingCache.batchPush(scgSettings, scgSettingTimeout);
        scgSettingDao.batchUpdate(scgSettings);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
