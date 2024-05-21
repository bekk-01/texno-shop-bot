package org.example.bot;

import lombok.extern.slf4j.Slf4j;
import org.example.enumerators.UserState;
import org.example.exception.DataNotFoundException;
import org.example.model.Computer;
import org.example.model.Phone;
import org.example.model.Tv;
import org.example.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.ArrayList;
import java.util.Objects;


@Slf4j
public class CommandHandler extends MyBot {
    public void handle(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();
        if (Objects.equals(text, "/start")) {
            try {
                execute(handleStart(chatId, message.getFrom()));
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        User currentUser = userService.findByChatId(chatId);
        if(text!=null) {
            handleShoppingOrSell(message);
        }

        switch (currentUser.getUserState()) {
            case SHARE_CONTACT -> {
                try {
                    execute(registrationHandler.handleContact(message, currentUser));
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case SHARE_LOCATION -> {
                try {
                    execute(registrationHandler.handleLocation(message, currentUser));
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case SELL_COMPUTER -> createProduct.createComputerFields(message);
            case SELL_PHONE -> createProduct.createPhoneFields(message);
            case SELL_TV -> createProduct.createTvFields(message);
            case SEARCHING -> createProduct.search(message);


        }
    }

    public void handleCallBackQuery(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        User user = userService.findByChatId(chatId);
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Computer") || text.equalsIgnoreCase("computer"))) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            ArrayList<Computer> computers = computerService.readFromFile();
            for (int i = 0; i < computers.size(); i++) {
                Computer computer = computers.get(i);
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId.toString());
                sendPhoto.setCaption("Model: " + computer.getModel() + "\n" +
                        "ModelBy: " + computer.getModelBy() + "\n" +
                        "Price: " + computer.getPrice() + "\n" +
                        "Ram: " + computer.getRam() + "\n" +
                        "Cup: " + computer.getCup() + "\n" +
                        "Count: " + computer.getCount());
                sendPhoto.setPhoto(new InputFile(computer.getFileId()));
                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setReplyMarkup(buttons.bucketsAdd());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Phone") || text.equalsIgnoreCase("phone"))) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            ArrayList<Phone> phones = phoneService.readFromFile();
            for (int i = 0; i < phones.size(); i++) {
                Phone phone = phones.get(i);
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId.toString());
                sendPhoto.setCaption("Model: " + phone.getModel() + "\n" +
                        "ModelBy: " + phone.getModelBy() + "\n" +
                        "Price: " + phone.getPrice() + "\n" +
                        "Battery: " + phone.getBattery() + "\n" +
                        "DisplayHrz: " + phone.getDisplayHrz() + "\n" +
                        "Count: " + phone.getCount());
                sendPhoto.setPhoto(new InputFile(phone.getFileId()));
                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setReplyMarkup(buttons.bucketsAdd());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Tv") || text.equalsIgnoreCase("tv"))) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            ArrayList<Tv> tvs = tvService.readFromFile();
            for (int i = 0; i < tvs.size(); i++) {
                Tv tv = tvs.get(i);
                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(chatId.toString());
                sendPhoto.setCaption("Model: " + tv.getModel() + "\n" +
                        "ModelBy: " + tv.getModelBy() + "\n" +
                        "Price: " + tv.getPrice() + "\n" +
                        "Dmu: " + tv.getDmu() + "\n" +
                        "Smart: " + tv.getSmart() + "\n" +
                        "Count: " + tv.getCount());
                sendPhoto.setPhoto(new InputFile(tv.getFileId()));
                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                sendMessage.setReplyMarkup(buttons.bucketsAdd());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Search") || text.equalsIgnoreCase("search"))){
            createProduct.searchCallback(callbackQuery);
            user.setUserState(UserState.SEARCHING);
            userService.update(user);

        }
        if ((user.getUserState() == UserState.SHOPPING || user.getUserState() == UserState.SELL ) && (Objects.equals(text, "Download") || text.equalsIgnoreCase("download"))){
            SendMessage sendMessage = new SendMessage(chatId.toString(),"Choose one↘️↘️");
            sendMessage.setReplyMarkup(buttons.downloadButton());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if((user.getUserState() == UserState.SHOPPING || user.getUserState() == UserState.SELL )&& (Objects.equals(text,"Excel")
                || text.equalsIgnoreCase("excel"))){
            downloading.handleExelFile(chatId);
        }
        if((user.getUserState() == UserState.SHOPPING || user.getUserState() == UserState.SELL )&& (Objects.equals(text,"Word")
                || text.equalsIgnoreCase("word"))){
            downloading.handleWordFile(chatId);
        }
        if(user.getUserState()==UserState.SELL && Objects.equals(text, "Computer")){
            createProduct.createComputer(callbackQuery);
        }
        if (user.getUserState() == UserState.SELL && Objects.equals(text,"Phone")){
            createProduct.createPhone(callbackQuery);
        }
        if (user.getUserState() == UserState.SELL && Objects.equals(text,"Tv")){
            createProduct.createTv(callbackQuery);
        }
    }

    public void handleShoppingOrSell(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();
        User user = userService.findByChatId(chatId);
        if (Objects.equals(text, "\uD83D\uDED2Shopping") || (Objects.equals(text, "Shopping")) || (Objects.equals(text, "shopping"))) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Choose one ↘️↘️");
            user.setUserState(UserState.SHOPPING);
            userService.update(user);
            sendMessage.setReplyMarkup(buttons.shoppingPage());
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (Objects.equals(text, "\uD83D\uDED2Sell") || text.equalsIgnoreCase("sell")) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "What do you sell ↘️↘️");
            user.setUserState(UserState.SELL);
            userService.update(user);
            sendMessage.setReplyMarkup(buttons.sellPage());
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public SendMessage handleStart(Long chatId, org.telegram.telegrambots.meta.api.objects.User from) {
        User user = null;
        try {
            user = userService.findByChatId(chatId);
            user.setUserState(UserState.MAIN_MENU);
            userService.update(user);
            SendMessage sendMessage = new SendMessage(chatId.toString(), String.format("Welcome back %s ", user.getFirstName()));
            sendMessage.setReplyMarkup(buttons.homePage());
            return sendMessage;
        } catch (DataNotFoundException e) {
            log.info(e.getMessage(), chatId);
            User newUser = User.builder()
                    .firstName(from.getFirstName())
                    .username(from.getUserName())
                    .userState(UserState.SHARE_CONTACT)
                    .chatId(chatId)
                    .build();
            user = userService.add(newUser);
        }
        SendMessage sendMessage = new SendMessage(chatId.toString(),
                String.format("Welcome %s, please share your number", user.getFirstName()));
        sendMessage.setReplyMarkup(buttons.requestContact());
        return sendMessage;
    }
}
