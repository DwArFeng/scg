package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.entity.GeneratorSupport;
import com.dwarfeng.scg.stack.service.GeneratorSupportMaintainService;
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
public class GeneratorSupportMaintainServiceImplTest {

    @Autowired
    private GeneratorSupportMaintainService service;

    private final List<GeneratorSupport> generatorSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            GeneratorSupport generatorSupport = new GeneratorSupport(
                    new StringIdKey("generator-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            generatorSupports.add(generatorSupport);
        }
    }

    @After
    public void tearDown() {
        generatorSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (GeneratorSupport generatorSupport : generatorSupports) {
                generatorSupport.setKey(service.insert(generatorSupport));
                service.update(generatorSupport);
                GeneratorSupport testGeneratorSupport = service.get(generatorSupport.getKey());
                assertEquals(BeanUtils.describe(generatorSupport), BeanUtils.describe(testGeneratorSupport));
            }
        } finally {
            for (GeneratorSupport generatorSupport : generatorSupports) {
                service.delete(generatorSupport.getKey());
            }
        }
    }
}
