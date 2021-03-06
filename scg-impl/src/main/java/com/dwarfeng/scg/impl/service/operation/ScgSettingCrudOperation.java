package com.dwarfeng.scg.impl.service.operation;

import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.scg.stack.cache.ScgSettingCache;
import com.dwarfeng.scg.stack.dao.ScgNodeInfoDao;
import com.dwarfeng.scg.stack.dao.ScgSettingDao;
import com.dwarfeng.scg.stack.service.ScgNodeInfoMaintainService;
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

    private final ScgNodeInfoDao scgNodeInfoDao;

    @Value("${cache.timeout.entity.scg_setting}")
    private long scgSettingTimeout;

    public ScgSettingCrudOperation(
            ScgSettingDao scgSettingDao, ScgSettingCache scgSettingCache,
            ScgNodeInfoDao scgNodeInfoDao
    ) {
        this.scgSettingDao = scgSettingDao;
        this.scgSettingCache = scgSettingCache;
        this.scgNodeInfoDao = scgNodeInfoDao;
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
        // ????????????????????????????????????????????????
        List<ScgNodeKey> scgNodeInfoKeys = scgNodeInfoDao.lookup(
                ScgNodeInfoMaintainService.SCG_SETTING_ID_EQUALS, new Object[]{key.getStringId()}
        ).stream().map(ScgNodeInfo::getKey).collect(Collectors.toList());
        scgNodeInfoDao.batchDelete(scgNodeInfoKeys);

        // ?????????????????????????????????
        scgSettingDao.delete(key);
        scgSettingCache.delete(key);
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
