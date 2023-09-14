package com.dwarfeng.scg.impl.service;

import com.dwarfeng.scg.stack.bean.dto.BatchGenerateInfo;
import com.dwarfeng.scg.stack.bean.dto.GenerateInfo;
import com.dwarfeng.scg.stack.handler.GenerateHandler;
import com.dwarfeng.scg.stack.service.PostStyleGenerateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostStyleGenerateServiceImpl implements PostStyleGenerateService {

    private final GenerateHandler generateHandler;

    private final ServiceExceptionMapper sem;

    public PostStyleGenerateServiceImpl(GenerateHandler generateHandler, ServiceExceptionMapper sem) {
        this.generateHandler = generateHandler;
        this.sem = sem;
    }

    @Override
    public String generate(GenerateInfo generateInfo) throws ServiceException {
        try {
            StringIdKey scgSettingKey = generateInfo.getScgSettingKey();
            return generateHandler.generate(scgSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("生成序列码时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public List<String> batchGenerate(BatchGenerateInfo batchGenerateInfo) throws ServiceException {
        try {
            StringIdKey scgSettingKey = batchGenerateInfo.getScgSettingKey();
            int size = batchGenerateInfo.getSize();
            return generateHandler.generate(scgSettingKey, size);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("批量生成序列码时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
