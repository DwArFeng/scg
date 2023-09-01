package com.dwarfeng.scg.impl.dao.preset;

import com.dwarfeng.scg.stack.service.CommonVariableMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class CommonVariablePresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case CommonVariableMaintainService.CHILD_FOR_SCG_SETTING:
                childForScgSetting(criteria, objs);
                break;
            case CommonVariableMaintainService.SCG_SETTING_ID_LIKE:
                scgSettingIdLike(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForScgSetting(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("scgSettingId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("scgSettingId", stringIdKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void scgSettingIdLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String pattern = (String) objects[0];
            detachedCriteria.add(Restrictions.like("scgSettingId", pattern, MatchMode.ANYWHERE));
            detachedCriteria.addOrder(Order.asc("scgSettingId"));
            detachedCriteria.addOrder(Order.asc("deviceId"));
            detachedCriteria.addOrder(Order.asc("variableId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
