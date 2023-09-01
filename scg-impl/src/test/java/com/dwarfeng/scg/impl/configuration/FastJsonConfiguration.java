package com.dwarfeng.scg.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonGeneratorSupport;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonNodeVariable;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgNodeInfo;
import com.dwarfeng.scg.sdk.bean.entity.FastJsonScgSetting;
import com.dwarfeng.scg.sdk.bean.key.FastJsonScgNodeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson auto-type 白名单...");
        ParserConfig.getGlobalInstance().addAccept(FastJsonGeneratorSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonScgSetting.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonScgNodeInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonScgNodeKey.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonNodeVariable.class.getCanonicalName());
        LOGGER.debug("FastJson auto-type 白名单配置完毕");
    }
}
