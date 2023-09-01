package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.scg.stack.bean.dto.GenerateResult;
import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.scg.stack.handler.GenerateHandler;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler;
import com.dwarfeng.scg.stack.handler.Generator;
import com.dwarfeng.scg.stack.handler.LockLocalCacheHandler;
import com.dwarfeng.scg.stack.service.ScgNodeInfoMaintainService;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;

@Component
public class GenerateHandlerImpl implements GenerateHandler {

    private final ScgSettingMaintainService scgSettingMaintainService;
    private final ScgNodeInfoMaintainService scgNodeInfoMaintainService;

    private final GenerateLocalCacheHandler generateLocalCacheHandler;
    private final LockLocalCacheHandler lockLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    @Value("${generate.device_id}")
    private int deviceId;

    public GenerateHandlerImpl(
            ScgSettingMaintainService scgSettingMaintainService,
            ScgNodeInfoMaintainService scgNodeInfoMaintainService,
            GenerateLocalCacheHandler generateLocalCacheHandler,
            LockLocalCacheHandler lockLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.scgSettingMaintainService = scgSettingMaintainService;
        this.scgNodeInfoMaintainService = scgNodeInfoMaintainService;
        this.generateLocalCacheHandler = generateLocalCacheHandler;
        this.lockLocalCacheHandler = lockLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public String generate(StringIdKey scgSettingKey) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);
            ScgSetting scgSetting = scgSettingMaintainService.get(scgSettingKey);

            // 根据 ScgSetting 的分布式模式调用不用的生成流程。
            if (scgSetting.isDistributed()) {
                // TODO 后续需要使用分布式锁技术进行支持。
                throw new UnsupportedOperationException("目前不支持分布式流水码生成");
            } else {
                return generateWithIndependent(scgSettingKey);
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String generateWithIndependent(StringIdKey scgSettingKey) throws Exception {
        Lock lock = lockLocalCacheHandler.get(scgSettingKey);
        lock.lock();
        try {
            ScgNodeInfo scgNodeInfo = scgNodeInfoMaintainService.getIfExists(
                    new ScgNodeKey(scgSettingKey.getStringId(), deviceId)
            );
            GenerateInfo generateInfo = new GenerateInfo(
                    Objects.isNull(scgNodeInfo) ? null : scgNodeInfo.getLastGeneratedDate(),
                    Objects.isNull(scgNodeInfo) ? null : scgNodeInfo.getLastIndex(),
                    deviceId,
                    false
            );
            Generator generator = generateLocalCacheHandler.get(scgSettingKey);
            GenerateResult generateResult = generator.generate(generateInfo);
            scgNodeInfo = new ScgNodeInfo(
                    new ScgNodeKey(scgSettingKey.getStringId(), deviceId), new Date(), generateResult.getNeoIndex()
            );
            scgNodeInfoMaintainService.insertOrUpdate(scgNodeInfo);
            return generateResult.getSerialCode();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public List<String> batchGenerate(StringIdKey scgSettingKey, int size) throws HandlerException {
        try {
            // 确认 scgSettingKey 存在。
            handlerValidator.makeSureScgSettingExists(scgSettingKey);
            ScgSetting scgSetting = scgSettingMaintainService.get(scgSettingKey);

            // 根据 ScgSetting 的分布式模式调用不用的生成流程。
            if (scgSetting.isDistributed()) {
                // TODO 后续需要使用分布式锁技术进行支持。
                throw new UnsupportedOperationException("目前不支持分布式流水码生成");
            } else {
                return batchGenerateWithIndependent(scgSettingKey, size);
            }
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private List<String> batchGenerateWithIndependent(StringIdKey scgSettingKey, int size) throws Exception {
        Lock lock = lockLocalCacheHandler.get(scgSettingKey);
        lock.lock();
        try {
            ScgNodeInfo scgNodeInfo = scgNodeInfoMaintainService.getIfExists(
                    new ScgNodeKey(scgSettingKey.getStringId(), deviceId)
            );
            GenerateInfo generateInfo = new GenerateInfo(
                    Objects.isNull(scgNodeInfo) ? null : scgNodeInfo.getLastGeneratedDate(),
                    Objects.isNull(scgNodeInfo) ? null : scgNodeInfo.getLastIndex(),
                    deviceId,
                    false
            );
            Generator generator = generateLocalCacheHandler.get(scgSettingKey);
            List<String> result = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                GenerateResult generateResult = generator.generate(generateInfo);
                result.add(generateResult.getSerialCode());
                generateInfo.setLastGeneratedDate(new Date());
                generateInfo.setLastIndex(generateResult.getNeoIndex());
            }
            scgNodeInfo = new ScgNodeInfo(
                    new ScgNodeKey(scgSettingKey.getStringId(), deviceId), new Date(), generateInfo.getLastIndex()
            );
            scgNodeInfoMaintainService.insertOrUpdate(scgNodeInfo);
            return result;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Generator getGenerator(StringIdKey scgSettingKey) throws HandlerException {
        return generateLocalCacheHandler.get(scgSettingKey);
    }

    @Override
    public void clearGenerateLocalCache() throws HandlerException {
        generateLocalCacheHandler.clear();
    }

    @Override
    public Lock getLocalLock(StringIdKey scgSettingKey) throws HandlerException {
        return lockLocalCacheHandler.get(scgSettingKey);
    }

    @Override
    public void clearLockLocalCache() throws HandlerException {
        lockLocalCacheHandler.clear();
    }
}
