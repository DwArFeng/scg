package com.dwarfeng.scg.impl.dao.preset;

import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.service.ScgNodeInfoMaintainService;
import com.dwarfeng.subgrade.sdk.memory.filter.PresetEntityFilter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ScgNodeInfoPresetEntityFilter implements PresetEntityFilter<ScgNodeInfo> {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public boolean accept(ScgNodeInfo entity, String preset, Object[] objs) {
        switch (preset) {
            case ScgNodeInfoMaintainService.SCG_SETTING_ID_EQUALS:
                return scgSettingIdEquals(entity, objs);
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    private boolean scgSettingIdEquals(ScgNodeInfo entity, Object[] objs) {
        try {
            String scgSettingId = (String) objs[0];
            return Objects.equals(entity.getKey().getScgSettingId(), scgSettingId);
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
