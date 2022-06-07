package com.dwarfeng.scg.impl.service.telqos;

import java.util.StringJoiner;

/**
 * 指令工具类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
final class CommandUtil {

    public static String syntax(String... patterns) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (String pattern : patterns) {
            sj.add(pattern);
        }
        return sj.toString();
    }

    public static String optionMismatchMessage(String... patterns) {
        StringJoiner sj = new StringJoiner(", --", "下列选项必须且只能含有一个: --", "");
        for (String pattern : patterns) {
            sj.add(pattern);
        }
        return sj.toString();
    }

    private CommandUtil() {
        throw new IllegalStateException("禁止实例化");
    }
}
