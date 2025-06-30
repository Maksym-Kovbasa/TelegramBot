package org.example.bot.controller;

import com.pengrad.telegrambot.model.Update;
import lombok.RequiredArgsConstructor;
import org.example.bot.service.CommandService;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class BotController {
    private final CommandService commandService;

    public void handleUpdate(Update update) {
        if (update.message() == null || update.message().text() == null) return;

        long chatId = update.message().chat().id();
        String messageText = update.message().text().trim();
        String userName = update.message().from().firstName();

        if (messageText.startsWith("/")) {
            commandService.processCommand(chatId, messageText, userName);
        } else {
            commandService.processButtonCommand(chatId, messageText, userName);
        }

    }
}