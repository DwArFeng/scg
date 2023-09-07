package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.bean.entity.ScgSetting;
import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler.GenerateContext;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 生成本地缓存处理器。
 *
 * <p>
 * 处理器在本地保存数据，缓存中的数据可以保证与数据源保持同步。
 *
 * <p>
 * 数据存放在本地，必要时才与数据访问层通信，这有助于程序效率的提升。
 *
 * <p>
 * 该处理器线程安全。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface GenerateLocalCacheHandler extends LocalCacheHandler<StringIdKey, GenerateContext> {

    /**
     * 生成器上下文。
     *
     * @since 1.2.0
     */
    final class GenerateContext {

        private final ScgSetting scgSetting;
        private final Generator generator;

        public GenerateContext(ScgSetting scgSetting, Generator generator) {
            this.scgSetting = scgSetting;
            this.generator = generator;
        }

        public ScgSetting getScgSetting() {
            return scgSetting;
        }

        public Generator getGenerator() {
            return generator;
        }

        @Override
        public String toString() {
            return "GenerateContext{" +
                    "scgSetting=" + scgSetting +
                    ", generator=" + generator +
                    '}';
        }
    }
}
