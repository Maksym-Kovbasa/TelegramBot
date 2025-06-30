package org.example.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Command {
    START("/start", "Start working with the bot"),
    HELP("/help", "Get help"),
    INFO("/info", "Information about the bot"),
    UNKNOWN("unknown", "Unknown command");

    private final String commandName;
    private final String description;

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.getCommandName().equals(text)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}