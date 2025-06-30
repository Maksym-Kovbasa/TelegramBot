
package org.example.bot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import jakarta.annotation.PostConstruct;
import org.example.bot.controller.BotController;
import org.springframework.stereotype.Component;

@Component
public class Bot {
    private final TelegramBot telegramBot;
    private final BotController botController;

    public Bot(TelegramBot telegramBot, BotController botController) {
        this.telegramBot = telegramBot;
        this.botController = botController;
    }

    @PostConstruct
    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(botController::handleUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}