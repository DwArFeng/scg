package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.service.ScgSettingMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ScgSettingMaintainServiceImplTest {

    @Autowired
    private ScgSettingMaintainService scgSettingMaintainService;

    private List<ScgSetting> scgSettings;

    @Before
    public void setUp() {
        scgSettings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ScgSetting scgSetting = new ScgSetting(
                    new StringIdKey("test.scg_setting." + i), "label", "remark", "type", "param", true
            );
            scgSettings.add(scgSetting);
        }
    }

    @After
    public void tearDown() {
        scgSettings.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ScgSetting scgSetting : scgSettings) {
                scgSettingMaintainService.insertOrUpdate(scgSetting);
                scgSettingMaintainService.update(scgSetting);
                ScgSetting testScgSetting = scgSettingMaintainService.get(scgSetting.getKey());
                assertEquals(BeanUtils.describe(scgSetting), BeanUtils.describe(testScgSetting));
            }
        } finally {
            for (ScgSetting scgSetting : scgSettings) {
                scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
            }
        }
    }
}
