package org.example.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Command {
    START("/start", "Start working with the bot", "Start"),
    HELP("/help", "Get help", "Help"),
    INFO("/info", "Information about the bot", "Info"),
    SOMETHING("/something", "Something special", "Something"),
    UNKNOWN("unknown", "Unknown command", "");

    private final String commandName;
    private final String description;
    private final String buttonLabel;

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.getCommandName().equals(text)) {
                return command;
            }
        }
        return UNKNOWN;
    }

    public static Command fromButtonLabel(String label) {
        for (Command command : Command.values()) {
            if (command.getButtonLabel().equalsIgnoreCase(label)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}