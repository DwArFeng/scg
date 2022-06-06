package com.dwarfeng.scg.stack.handler;

import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.scg.stack.bean.dto.GenerateResult;
import com.dwarfeng.scg.stack.exception.GeneratorException;

/**
 * 生成器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface Generator {

    /**
     * 生成串码及关联信息。
     *
     * @param generateInfo 与生成串码相关的信息。
     * @return 生成结果，包含串码本身和其它相关信息。
     * @throws GeneratorException 生成器异常。
     */
    GenerateResult generate(GenerateInfo generateInfo) throws GeneratorException;
}
