package com.dwarfeng.scg.impl.service.telqos;

import com.dwarfeng.scg.stack.service.GeneratorSupportQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class SupportCommand extends CliCommand {

    private static final String COMMAND_OPTION_RESET_GENERATOR = "reset-generator";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_GENERATOR,
    };

    private static final String IDENTITY = "support";
    private static final String DESCRIPTION = "支持操作";

    private static final String CMD_LINE_SYNTAX_RESET_GENERATOR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_GENERATOR);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_RESET_GENERATOR,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final GeneratorSupportQosService generatorSupportQosService;

    public SupportCommand(GeneratorSupportQosService generatorSupportQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.generatorSupportQosService = generatorSupportQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder().longOpt(COMMAND_OPTION_RESET_GENERATOR).desc("重置生成器支持").build());
        return list;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
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
                case COMMAND_OPTION_RESET_GENERATOR:
                    generatorSupportQosService.reset();
                    context.sendMessage("重置生成器支持成功。");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
