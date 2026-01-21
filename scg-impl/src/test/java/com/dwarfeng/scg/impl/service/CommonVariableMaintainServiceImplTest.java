package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.entity.CommonVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.CommonVariableKey;
import com.dwarfeng.scg.stack.service.CommonVariableMaintainService;
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
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class CommonVariableMaintainServiceImplTest {

    private static final String TEST_SCG_SETTING_ID = "test.scg_setting";

    @Autowired
    private ScgSettingMaintainService scgSettingMaintainService;
    @Autowired
    private CommonVariableMaintainService commonVariableMaintainService;

    private ScgSetting scgSetting;
    private List<CommonVariable> commonVariables;

    @Before
    public void setUp() {
        scgSetting = new ScgSetting(
                new StringIdKey(TEST_SCG_SETTING_ID), "label", "remark", "type", "param", true, 12450
        );
        commonVariables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CommonVariable commonVariable = new CommonVariable(
                    new CommonVariableKey(TEST_SCG_SETTING_ID, i + ""),
                    "stringValue", true, 12450, 12450L, 12.450, new Date(), new Date()
            );
            commonVariables.add(commonVariable);
        }
    }

    @After
    public void tearDown() {
        scgSetting = null;
        commonVariables.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            scgSettingMaintainService.insertOrUpdate(scgSetting);
            for (CommonVariable commonVariable : commonVariables) {
                commonVariableMaintainService.insertOrUpdate(commonVariable);
                commonVariableMaintainService.update(commonVariable);
                CommonVariable testCommonVariable = commonVariableMaintainService.get(commonVariable.getKey());
                assertEquals(BeanUtils.describe(commonVariable), BeanUtils.describe(testCommonVariable));
            }
        } finally {
            for (CommonVariable commonVariable : commonVariables) {
                if (Objects.isNull(commonVariable.getKey())) {
                    continue;
                }
                commonVariableMaintainService.deleteIfExists(commonVariable.getKey());
            }
            if (Objects.nonNull(scgSetting.getKey())) {
                scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
            }
        }
    }

    @Test
    public void testForScgSettingCascade() throws Exception {
        try {
            scgSettingMaintainService.insertOrUpdate(scgSetting);
            for (CommonVariable commonVariable : commonVariables) {
                commonVariableMaintainService.insertOrUpdate(commonVariable);
            }

            assertEquals(commonVariables.size(), commonVariableMaintainService.lookup(
                    CommonVariableMaintainService.CHILD_FOR_SCG_SETTING,
                    new Object[]{scgSetting.getKey()}).getCount()
            );

            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());

            assertEquals(0, commonVariableMaintainService.lookup(
                    CommonVariableMaintainService.CHILD_FOR_SCG_SETTING,
                    new Object[]{scgSetting.getKey()}).getCount()
            );
            assertTrue(commonVariableMaintainService.nonExists(
                    commonVariables.stream().map(CommonVariable::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (CommonVariable commonVariable : commonVariables) {
                if (Objects.isNull(commonVariable.getKey())) {
                    continue;
                }
                commonVariableMaintainService.deleteIfExists(commonVariable.getKey());
            }
            if (Objects.nonNull(scgSetting.getKey())) {
                scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
            }
        }
    }
}
