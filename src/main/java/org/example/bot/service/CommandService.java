package org.example.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.bot.model.Command;
import org.springframework.stereotype.Service;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final TelegramBot bot;
    private final MessageService messageService;

    public void processCommand(Long chatId, String commandName, String userName) {
        Command command = Command.fromString(commandName);
        String responseText = switch (command) {
            case START -> messageService.getStartMessage(userName);
            case HELP -> messageService.getHelpMessage();
            case INFO -> messageService.getInfoMessage();
            case SOMETHING -> "This is something special!";
            case UNKNOWN -> messageService.getUnknownCommandMessage();
        };

        SendMessage message = new SendMessage(chatId, responseText);

        if (command == Command.HELP) {
            message.replyMarkup(createHelpKeyboard());
        } else {
            message.replyMarkup(new ReplyKeyboardRemove(true));
        }

        bot.execute(message);
    }

    private ReplyKeyboardMarkup createHelpKeyboard() {
        String[] labels = Stream.of(Command.values())
                .filter(cmd -> cmd != Command.UNKNOWN)
                .map(Command::getButtonLabel)
                .toArray(String[]::new);

        List<String[]> rows = new ArrayList<>();
        for (int i = 0; i < labels.length; i += 2) {
            int end = Math.min(i + 2, labels.length);
            rows.add(Arrays.copyOfRange(labels, i, end));
        }

        return new ReplyKeyboardMarkup(rows.toArray(new String[0][]))
                .resizeKeyboard(true)
                .selective(true);
    }

    public void processButtonCommand(Long chatId, String buttonLabel, String userName) {
        Command command = Command.fromButtonLabel(buttonLabel);
        processCommand(chatId, command.getCommandName(), userName);
    }
}