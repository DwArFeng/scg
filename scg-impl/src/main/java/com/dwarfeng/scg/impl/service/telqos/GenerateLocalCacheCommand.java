package com.dwarfeng.scg.impl.service.telqos;

import com.dwarfeng.scg.stack.handler.GenerateLocalCacheHandler.GenerateContext;
import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class GenerateLocalCacheCommand extends CliCommand {

    private static final String COMMAND_OPTION_LOOKUP = "l";
    private static final String COMMAND_OPTION_CLEAR = "c";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_LOOKUP,
            COMMAND_OPTION_CLEAR
    };

    private static final String IDENTITY = "glc";
    private static final String DESCRIPTION = "生成器本地缓存操作";

    private static final String CMD_LINE_SYNTAX_LOOKUP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_LOOKUP) + " scg-setting-id";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_LOOKUP,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    public GenerateLocalCacheCommand(GenerateQosService generateQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.generateQosService = generateQosService;
    }

    private final GenerateQosService generateQosService;

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_LOOKUP).hasArg().desc("查询生成上下文").type(String.class).build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).desc("清除生成器").build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_LOOKUP:
                    handleLookup(context, cmd);
                    break;
                case COMMAND_OPTION_CLEAR:
                    generateQosService.clearGenerateLocalCache();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleLookup(Context context, CommandLine cmd) throws Exception {
        StringIdKey scgSettingKey = new StringIdKey(cmd.getOptionValue(COMMAND_OPTION_LOOKUP));
        GenerateContext generateContext = generateQosService.getGenerateContext(scgSettingKey);
        if (Objects.isNull(generateContext)) {
            context.sendMessage("not exists");
        } else {
            context.sendMessage(generateContext.toString());
        }
    }
}
