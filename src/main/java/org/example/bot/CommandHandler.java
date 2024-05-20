package org.example.bot;

import lombok.extern.slf4j.Slf4j;
import org.example.enumerators.UserState;
import org.example.exception.DataNotFoundException;
import org.example.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.MaybeInaccessibleMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;
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
        if(text!=null){
            handleShoppingOrSell(message);
        }
        switch (currentUser.getUserState()) {
            case SHARE_CONTACT -> {
                try {
                    execute(registrationHandler.handleContact(message, currentUser));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case SHARE_LOCATION -> {
                try {
                    execute(registrationHandler.handleLocation(message, currentUser));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case SELL -> createProduct.createComputerFields(message);


        }
    }

    public void handleCallBackQuery(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        User user = userService.findByChatId(chatId);
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Computer") || text.equalsIgnoreCase("computer"))) {
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            sendMessage.setReplyMarkup(buttons.bucketsAdd());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Phone") || text.equalsIgnoreCase("phone"))) {

            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            sendMessage.setReplyMarkup(buttons.bucketsAdd());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (user.getUserState() == UserState.SHOPPING && (Objects.equals(text, "Tv") || text.equalsIgnoreCase("tv"))) {

            SendMessage sendMessage = new SendMessage(chatId.toString(), "Would you like to buy↘️↘️");
            sendMessage.setReplyMarkup(buttons.bucketsAdd());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getUserState()==UserState.SELL && Objects.equals(text, "Computer")){
            createProduct.createComputer(callbackQuery);
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
