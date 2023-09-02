package com.dwarfeng.scg.impl.handler;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.exception.ScgSettingDisabledException;
import com.dwarfeng.scg.stack.exception.ScgSettingNotExistsException;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 处理器验证器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class HandlerValidator {

    private final ScgSettingMaintainService scgSettingMaintainService;

    public HandlerValidator(
            ScgSettingMaintainService scgSettingMaintainService
    ) {
        this.scgSettingMaintainService = scgSettingMaintainService;
    }

    public void makeSureScgSettingExists(StringIdKey scgSettingKey) throws HandlerException {
        try {
            if (Objects.isNull(scgSettingKey) || !scgSettingMaintainService.exists(scgSettingKey)) {
                throw new ScgSettingNotExistsException(scgSettingKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureScgSettingEnabled(StringIdKey scgSettingKey) throws HandlerException {
        try {
            if (Objects.isNull(scgSettingKey) || !scgSettingMaintainService.exists(scgSettingKey)) {
                throw new ScgSettingNotExistsException(scgSettingKey);
            }
            ScgSetting scgSetting = scgSettingMaintainService.get(scgSettingKey);
            if (!scgSetting.isEnabled()) {
                throw new ScgSettingDisabledException(scgSettingKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
