package com.dwarfeng.scg.impl.service.telqos;

import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class GenerateCommand extends CliCommand {

    private static final String CMD_OPTION_SCG_SETTING_ID = "id";
    private static final String CMD_OPTION_SIZE = "s";
    private static final String CMD_OPTION_FILE = "f";

    private static final String IDENTITY = "gen";
    private static final String DESCRIPTION = "流水码生成/导出操作";

    private static final String CMD_LINE_SYNTAX = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(CMD_OPTION_SCG_SETTING_ID) + " scg-setting-id [" +
            CommandUtil.concatOptionPrefix(CMD_OPTION_SIZE) + " size] [" +
            CommandUtil.concatOptionPrefix(CMD_OPTION_FILE) + " [file-path]]";

    private static final int DEFAULT_SIZE = 5;
    private static final String DEFAULT_FILE_NAME = "export.csv";

    public GenerateCommand(GenerateQosService generateQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.generateQosService = generateQosService;
    }

    private final GenerateQosService generateQosService;

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(CMD_OPTION_SCG_SETTING_ID).hasArg().argName("scg-setting-id")
                        .desc("流水码生成设置 ID").type(String.class).required().build()
        );
        list.add(
                Option.builder(CMD_OPTION_SIZE).hasArg().argName("size").desc("生成数量").type(Number.class)
                        .build()
        );
        list.add(
                Option.builder(CMD_OPTION_FILE).optionalArg(true).hasArg(true).argName("file-path")
                        .desc("生成的CSV的路径").type(String.class).build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            String scgSettingId = ((String) cmd.getParsedOptionValue(CMD_OPTION_SCG_SETTING_ID));
            int size;
            if (cmd.hasOption(CMD_OPTION_SIZE)) {
                Number sizeNumber = (Number) cmd.getParsedOptionValue(CMD_OPTION_SIZE);
                if (Objects.isNull(sizeNumber)) {
                    size = DEFAULT_SIZE;
                } else {
                    size = sizeNumber.intValue();
                }
            } else {
                context.sendMessage("请输入需要生成的 ID 的数量:");
                size = Integer.parseInt(context.receiveMessage());
            }
            List<String> serialCodes = generateQosService.batchGenerate(new StringIdKey(scgSettingId), size);
            if (cmd.hasOption(CMD_OPTION_FILE)) {
                print2File(serialCodes, context, cmd);
            } else {
                print2Screen(serialCodes, context);
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void print2File(List<String> serialCodes, Context context, CommandLine cmd) throws Exception {
        File file;

        String optionFileName = (String) cmd.getParsedOptionValue(CMD_OPTION_FILE);
        if (Objects.nonNull(optionFileName)) {
            file = new File((optionFileName));
            if (file.isDirectory()) {
                file = new File(file, DEFAULT_FILE_NAME);
            }
        } else {
            file = new File(DEFAULT_FILE_NAME);
        }

        if (file.exists()) {
            while (true) {
                context.sendMessage("文件 \"" + file.getName() + "\" 已经存在，是否覆盖？ Y/N");
                String option = context.receiveMessage().toUpperCase();
                if (Objects.equals(option, "Y")) {
                    break;
                } else if (Objects.equals(option, "N")) {
                    context.sendMessage("操作已取消！");
                    return;
                }
            }
        }

        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw, 4096)
        ) {
            for (String serialCode : serialCodes) {
                bw.write(serialCode);
                bw.write(System.lineSeparator());
            }
            bw.flush();
        }
        context.sendMessage("CSV 文件已导出至 " + file.getAbsolutePath());
    }

    private void print2Screen(List<String> serialCodes, Context context) throws Exception {
        int digits = digits(serialCodes.size() - 1);
        for (int i = 0; i < serialCodes.size(); i++) {
            String serialCode = serialCodes.get(i);
            context.sendMessage(String.format("%" + digits + "d: %s", i, serialCode));
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
