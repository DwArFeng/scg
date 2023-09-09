package com.dwarfeng.scg.api.integration.subgrade;

import com.dwarfeng.dutil.basic.io.CT;
import com.dwarfeng.subgrade.stack.exception.GenerateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ScgStringGeneratorTest {

    @Autowired
    private ScgStringGenerator generator;

    @Test
    public void testGenerate() throws GenerateException {
        for (int i = 0; i < 100; i++) {
            CT.trace(generator.generate());
        }
    }

    @Test
    public void testBatchGenerate() throws GenerateException {
        List<String> strings = generator.batchGenerate(100);
        strings.forEach(CT::trace);
    }
}
