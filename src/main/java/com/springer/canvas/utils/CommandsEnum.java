package com.springer.canvas.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum CommandsEnum {
    CREATE("[Cc] \\d+ \\d+"),
    LINE("[Ll] \\d+ \\d+ \\d+ \\d+"),
    RECTANGLE("[Rr] \\d+ \\d+ \\d+ \\d+"),
    BUCKET_FILL("[Bb] \\d+ \\d+ [a-zA-Z0-9]"),
    QUIT("[Qq]");

    private final Pattern pattern;
    private static final List<CommandsEnum> COMMANDS_ENUM_LIST = Arrays.asList(CommandsEnum.values());

    CommandsEnum(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    // Check if parameters are valid or throw an exception
    public static CommandsEnum getCommand(String command) {
        return COMMANDS_ENUM_LIST.stream()
                .filter(enumValue -> enumValue.pattern.matcher(command).matches())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
