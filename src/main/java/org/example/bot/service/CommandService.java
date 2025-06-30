package org.example.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.example.bot.model.Command;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final TelegramBot bot;
    private final MessageService messageService;

    public void processCommand(String commandName, Long chatId, String userName) {
        Command command = Command.fromString(commandName);
        String responseText =
                switch (command) {
                    case START -> messageService.getStartMessage(userName);
                    case HELP -> messageService.getHelpMessage();
                    case INFO -> messageService.getInfoMessage();
                    case UNKNOWN -> messageService.getUnknownCommandMessage();
                };
        bot.execute(new SendMessage(chatId, responseText));
    }
}
