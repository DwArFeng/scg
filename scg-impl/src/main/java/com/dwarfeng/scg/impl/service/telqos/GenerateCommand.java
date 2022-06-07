package com.dwarfeng.scg.impl.service.telqos;

import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenerateCommand extends CliCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateCommand.class);

    private static final String COMMAND_OPTION_SCG_SETTING_KEY = "s";
    private static final String COMMAND_OPTION_SIZE = "i";

    private static final String IDENTITY = "gen";
    private static final String DESCRIPTION = "数据记录本地缓存操作";

    private static final String CMD_LINE_SYNTAX = "gen -" + COMMAND_OPTION_SCG_SETTING_KEY +
            " [id] " + "-" + COMMAND_OPTION_SIZE + " [size]";

    public GenerateCommand(GenerateQosService generateQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.generateQosService = generateQosService;
    }

    private final GenerateQosService generateQosService;

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SCG_SETTING_KEY).hasArg().desc("流水码生成设置ID").type(String.class).required().build());
        list.add(Option.builder(COMMAND_OPTION_SIZE).hasArg().desc("流水码生成数量").type(Number.class).required().build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            String scgSettingId = ((String) cmd.getParsedOptionValue(COMMAND_OPTION_SCG_SETTING_KEY));
            int size;
            try {
                size = ((Number) cmd.getParsedOptionValue(COMMAND_OPTION_SIZE)).intValue();
            } catch (ParseException e) {
                LOGGER.warn("解析命令选项时发生异常，异常信息如下", e);
                context.sendMessage("命令行格式错误，正确的格式为: " + CMD_LINE_SYNTAX);
                context.sendMessage("请留意选项 " + COMMAND_OPTION_SIZE + " 后接参数的类型应该是数字 ");
                return;
            }
            List<String> result = generateQosService.batchGenerate(new StringIdKey(scgSettingId), size);
            int digits = digits(result.size());
            for (int i = 0; i < result.size(); i++) {
                String serialCode = result.get(i);
                context.sendMessage(String.format("%" + digits + "d: %s", i, serialCode));
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private int digits(int target) {
        int digits = 0;
        do {
            digits++;
        } while ((target /= 10) > 0);
        return digits;
    }
}
