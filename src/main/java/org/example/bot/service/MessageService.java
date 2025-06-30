package org.example.bot.service;

import org.example.bot.model.Command;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class MessageService {

    public String getStartMessage(String userName) {
        return String.format("Hello, %s,\n" +
                "Welcome to our bot!\n" +
                "Use /help to get a list of command", userName);
    }

    public String getHelpMessage() {
        return "Available commands:\n\n" +
                Arrays.stream(Command.values())
                        .filter(cmd -> cmd != Command.UNKNOWN)
                        .map(cmd -> cmd.getCommandName() + " - " + cmd.getDescription())
                        .collect(Collectors.joining("\n"));
    }


    public String getInfoMessage() {
        return """
                Version: 0.0.1
                Author: @Kovbasa_M
                Description: Simple telegram bot with MVC architecture
                """;
    }

    public String getUnknownCommandMessage() {
        return "Command not found";
    }
}
