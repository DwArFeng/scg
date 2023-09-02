package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.entity.NodeVariable;
import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.bean.key.NodeVariableKey;
import com.dwarfeng.scg.stack.service.NodeVariableMaintainService;
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
public class NodeVariableMaintainServiceImplTest {

    private static final String TEST_SCG_SETTING_ID = "test.scg_setting";

    @Autowired
    private ScgSettingMaintainService scgSettingMaintainService;
    @Autowired
    private NodeVariableMaintainService nodeVariableMaintainService;

    private ScgSetting scgSetting;
    private List<NodeVariable> nodeVariables;

    @Before
    public void setUp() {
        scgSetting = new ScgSetting(
                new StringIdKey(TEST_SCG_SETTING_ID), "label", "remark", "type", "param", true
        );
        nodeVariables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NodeVariable nodeVariable = new NodeVariable(
                    new NodeVariableKey(TEST_SCG_SETTING_ID, 12450, i + ""),
                    "stringValue", true, 12450, 12450L, 12.450, new Date(), new Date()
            );
            nodeVariables.add(nodeVariable);
        }
    }

    @After
    public void tearDown() {
        scgSetting = null;
        nodeVariables.clear();
    }

    @Test
    public void testForCurd() throws Exception {
        try {
            scgSettingMaintainService.insertOrUpdate(scgSetting);
            for (NodeVariable nodeVariable : nodeVariables) {
                nodeVariableMaintainService.insertOrUpdate(nodeVariable);
                nodeVariableMaintainService.update(nodeVariable);
                NodeVariable testNodeVariable = nodeVariableMaintainService.get(nodeVariable.getKey());
                assertEquals(BeanUtils.describe(nodeVariable), BeanUtils.describe(testNodeVariable));
            }
        } finally {
            for (NodeVariable nodeVariable : nodeVariables) {
                nodeVariableMaintainService.deleteIfExists(nodeVariable.getKey());
            }
            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
        }
    }

    @Test
    public void testForScgSettingCascade() throws Exception {
        try {
            scgSettingMaintainService.insertOrUpdate(scgSetting);
            for (NodeVariable nodeVariable : nodeVariables) {
                nodeVariableMaintainService.insertOrUpdate(nodeVariable);
            }

            assertEquals(nodeVariables.size(), nodeVariableMaintainService.lookup(
                    NodeVariableMaintainService.CHILD_FOR_SCG_SETTING,
                    new Object[]{scgSetting.getKey()}).getCount()
            );

            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());

            assertEquals(0, nodeVariableMaintainService.lookup(
                    NodeVariableMaintainService.CHILD_FOR_SCG_SETTING,
                    new Object[]{scgSetting.getKey()}).getCount()
            );
            assertTrue(nodeVariableMaintainService.nonExists(
                    nodeVariables.stream().map(NodeVariable::getKey).collect(Collectors.toList())
            ));
        } finally {
            for (NodeVariable nodeVariable : nodeVariables) {
                nodeVariableMaintainService.deleteIfExists(nodeVariable.getKey());
            }
            scgSettingMaintainService.deleteIfExists(scgSetting.getKey());
        }
    }
}
