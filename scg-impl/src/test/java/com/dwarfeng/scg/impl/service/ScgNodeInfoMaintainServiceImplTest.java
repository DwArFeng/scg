package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.entity.ScgNodeInfo;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.ScgNodeKey;
import com.dwarfeng.scg.stack.service.ScgNodeInfoMaintainService;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ScgNodeInfoMaintainServiceImplTest {

    private static final String TEST_SCG_SETTING_ID = "test.scg_setting";

    @Autowired
    private ScgSettingMaintainService scgSettingMaintainService;
    @Autowired
    private ScgNodeInfoMaintainService scgNodeInfoMaintainService;

    private ScgSetting scgSetting;
    private List<ScgNodeInfo> scgNodeInfos;

    @Before
    public void setUp() {
        scgSetting = new ScgSetting(
                new StringIdKey(TEST_SCG_SETTING_ID), "label", "remark", "type", "param", true, true
        );
        scgNodeInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ScgNodeInfo scgNodeInfo = new ScgNodeInfo(new ScgNodeKey(TEST_SCG_SETTING_ID, i), new Date(), 12450);
            scgNodeInfos.add(scgNodeInfo);
        }
    }

    @After
    public void tearDown() {
        scgSetting = null;
        scgNodeInfos.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            scgSettingMaintainService.insert(scgSetting);
            for (ScgNodeInfo scgNodeInfo : scgNodeInfos) {
                scgNodeInfoMaintainService.insertOrUpdate(scgNodeInfo);
                scgNodeInfoMaintainService.update(scgNodeInfo);
                ScgNodeInfo testScgNodeInfo = scgNodeInfoMaintainService.get(scgNodeInfo.getKey());
                assertEquals(BeanUtils.describe(scgNodeInfo), BeanUtils.describe(testScgNodeInfo));
            }
        } finally {
            for (ScgNodeInfo scgNodeInfo : scgNodeInfos) {
                scgNodeInfoMaintainService.deleteIfExists(scgNodeInfo.getKey());
            }
            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
        }
    }

    @Test
    public void testForScgSettingCascade() throws Exception {
        try {
            scgSettingMaintainService.insert(scgSetting);
            for (ScgNodeInfo scgNodeInfo : scgNodeInfos) {
                scgNodeInfoMaintainService.insertOrUpdate(scgNodeInfo);
            }

            assertEquals(scgNodeInfos.size(), scgNodeInfoMaintainService.lookup(
                    ScgNodeInfoMaintainService.SCG_SETTING_ID_EQUALS,
                    new Object[]{scgSetting.getKey().getStringId()}).getCount()
            );

            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());

            assertEquals(0, scgNodeInfoMaintainService.lookup(
                    ScgNodeInfoMaintainService.SCG_SETTING_ID_EQUALS,
                    new Object[]{scgSetting.getKey().getStringId()}).getCount()
            );
            assertTrue(scgNodeInfoMaintainService.nonExists(
                    scgNodeInfos.stream().map(ScgNodeInfo::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (ScgNodeInfo scgNodeInfo : scgNodeInfos) {
                scgNodeInfoMaintainService.deleteIfExists(scgNodeInfo.getKey());
            }
            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
        }
    }
}
