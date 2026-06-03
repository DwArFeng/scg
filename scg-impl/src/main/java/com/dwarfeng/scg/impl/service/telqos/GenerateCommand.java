package com.dwarfeng.scg.impl.service.telqos;

import com.dwarfeng.scg.stack.service.GenerateQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class GenerateCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "gen";

    // region 指令选项

    private static final String COMMAND_OPTION_SCG_SETTING_ID = "id";
    private static final String COMMAND_OPTION_SIZE = "s";
    private static final String COMMAND_OPTION_FILE = "f";

    // endregion

    private static final int DEFAULT_SIZE = 5;
    private static final String DEFAULT_FILE_NAME = "export.csv";

    private final GenerateQosService generateQosService;

    public GenerateCommand(GenerateQosService generateQosService) {
        super(IDENTITY);
        this.generateQosService = generateQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "流水码生成/导出操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_SCG_SETTING_ID) +
                        " scg-setting-id [" + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_SIZE) + " size] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_FILE) + " [file-path]]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_SCG_SETTING_ID).hasArg().argName("scg-setting-id")
                        .desc("流水码生成设置 ID").type(String.class).required().build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SIZE).hasArg().argName("size").desc("生成数量").type(Number.class)
                        .build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_FILE).optionalArg(true).hasArg(true).argName("file-path")
                        .desc("生成的 CSV 的路径").type(String.class).build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        String scgSettingId = parseScgSettingId(context, cmd);
        int size = parseSize(context, cmd);
        List<String> serialCodes = generateQosService.generate(new StringIdKey(scgSettingId), size);
        if (cmd.hasOption(COMMAND_OPTION_FILE)) {
            print2File(serialCodes, context, cmd);
        } else {
            print2Screen(serialCodes, context);
        }
    }

    // 为了程序的可读性，此处代码不做简化。
    @SuppressWarnings("unused")
    private String parseScgSettingId(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        String scgSettingId = (String) cmd.getParsedOptionValue(COMMAND_OPTION_SCG_SETTING_ID);
        if (StringUtils.isBlank(scgSettingId)) {
            throw new IllegalArgumentException("流水码生成设置 ID 不能为空");
        }
        return scgSettingId;
    }

    private int parseSize(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        if (cmd.hasOption(COMMAND_OPTION_SIZE)) {
            Number sizeNumber = (Number) cmd.getParsedOptionValue(COMMAND_OPTION_SIZE);
            if (Objects.isNull(sizeNumber)) {
                return DEFAULT_SIZE;
            }
            return sizeNumber.intValue();
        }
        context.sendMessage("请输入需要生成的 ID 的数量:");
        return Integer.parseInt(context.receiveMessage());
    }

    private File parseExportFile(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        File file;
        String optionFileName = (String) cmd.getParsedOptionValue(COMMAND_OPTION_FILE);
        if (Objects.nonNull(optionFileName)) {
            file = new File(optionFileName);
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
                    return null;
                }
            }
        }
        return file;
    }

    private void print2File(List<String> serialCodes, CommandExecutor.Context context, CommandLine cmd)
            throws Exception {
        File file = parseExportFile(context, cmd);
        if (Objects.isNull(file)) {
            return;
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

    private void print2Screen(List<String> serialCodes, CommandExecutor.Context context) throws Exception {
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
