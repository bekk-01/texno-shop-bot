package org.example.bot.product;

import org.example.bot.MyBot;
import org.example.model.Computer;
import org.example.model.Phone;
import org.example.model.Product;
import org.example.model.Tv;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductMenu extends MyBot {
    public void handleComputer(CallbackQuery callbackQuery) {
        ArrayList<Computer> computers = computerService.readFromFile();
        Computer computer = computers.get(0);
        Long chatId = callbackQuery.getMessage().getChatId();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Model: " + computer.getModel() + "\n"
                + "Model By: " + computer.getModelBy() + "\n" + "Price: " + computer.getPrice() + "\n"
                + "Ram: " + computer.getRam() + "\n" + "Cup: " + computer.getCup() + "\n"
                + "Count: " + computer.getCount());
        sendPhoto.setPhoto(new InputFile(new File("src/main/resources/photosComputer/img.png")));
        try {
            execute(sendPhoto);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void handlePhone(CallbackQuery callbackQuery) {
        ArrayList<Phone> phones = phoneService.readFromFile();
        Phone phone = phones.get(0);
        Long chatId = callbackQuery.getMessage().getChatId();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Model: " + phone.getModel() + "\n" +
                "ModelBy: " + phone.getModelBy() + "\n" +
                "Price: " + phone.getPrice() + "\n" +
                "Battery: " + phone.getBattery() + "\n" +
                "DisplayHrz: " + phone.getDisplayHrz() + "\n" +
                "Count: " + phone.getCount());
        sendPhoto.setPhoto(new InputFile(new File("src/main/resources/photosPhone/img.png")));
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
    public void handleTv(CallbackQuery callbackQuery){
        ArrayList<Tv> all = tvService.getAll();
        Tv tv = all.get(0);
        Long chatId = callbackQuery.getMessage().getChatId();
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setCaption("Model: " + tv.getModel()+"\n" +
                "ModelBy: " + tv.getModelBy() + "\n" +
                "Price: " + tv.getPrice()+ "\n" +
                "Dmu: " + tv.getDmu() + "\n"
                + "Count: " + tv.getCount());
        sendPhoto.setPhoto(new InputFile(new File("src/main/resources/photosTv/img.png")));
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void search(CallbackQuery callbackQuery){
        String text = callbackQuery.getData();
        ArrayList<Product> all = productService.getAll();
        for (Product product1 : all) {

        }


    }
}
