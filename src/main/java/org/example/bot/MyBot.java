package org.example.bot;

import org.example.bot.downloading.Downloading;
import org.example.bot.product.CreateProduct;
import org.example.service.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {
    public static UserService userService = UserService.getInstance();
    public static Buttons buttons = new Buttons();
    public static CommandHandler commandHandler = new CommandHandler();
    public static RegistrationHandler registrationHandler = new RegistrationHandler();
    public static ComputerService computerService = ComputerService.getInstance();
    public static PhoneService phoneService = PhoneService.getInstance();
    public static TvService tvService = TvService.getInstance();
    public static BucketService bucketService = BucketService.getInstance();
    public static CreateProduct createProduct = new CreateProduct();
    public static Downloading downloading = new Downloading();


    public MyBot() {
        super("6777158162:AAGuGf4YCKG7qyeuYYt9lxKHdhvGE7FOZ7w");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            commandHandler.handle(update.getMessage());
        }else if(update.hasCallbackQuery()){
           commandHandler.handleCallBackQuery(update.getCallbackQuery());
        }
    }

    @Override
    public String getBotUsername() {
        return "t.me/texno_texno_bot";
    }
}
