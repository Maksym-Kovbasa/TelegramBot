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
        if (update.message() != null && update.message().text() != null) {
            commandService.processCommand(
                    update.message().text(),
                    update.message().chat().id(),
                    update.message().from().firstName()
            );
        }
    }
}
