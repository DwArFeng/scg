package com.dwarfeng.scg.stack.service;

import com.dwarfeng.scg.stack.bean.dto.BatchGenerateInfo;
import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import java.util.List;

/**
 * Post 风格的生成服务。
 *
 * @author DwArFeng
 * @since 1.2.2
 */
public interface PostStyleGenerateService extends Service {

    /**
     * 生成序列码。
     *
     * @param generateInfo 生成信息。
     * @return 生成的序列码。
     * @throws ServiceException 服务异常。
     */
    String generate(GenerateInfo generateInfo) throws ServiceException;

    /**
     * 批量生成序列码。
     *
     * @param batchGenerateInfo 批量生成信息。
     * @return 生成的序列码组成的列表。
     * @throws ServiceException 服务异常。
     */
    List<String> batchGenerate(BatchGenerateInfo batchGenerateInfo) throws ServiceException;
}
