package org.example.bot;

import org.example.service.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    public static UserService userService = UserService.getInstance();
    public static Buttons buttons = new Buttons();
    public static CommandHandler commandHandler = new CommandHandler();
    RegistrationHandler registrationHandler = new RegistrationHandler();
    public MyBot() {
        super("6777158162:AAGuGf4YCKG7qyeuYYt9lxKHdhvGE7FOZ7w");
    }

    @Override
    public void onUpdateReceived(Update update) {
        commandHandler.handle(update.getMessage());
    }

    @Override
    public String getBotUsername() {
        return "t.me/texno_texno_bot";
    }
}
