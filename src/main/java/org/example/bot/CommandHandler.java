package org.example.bot;

import lombok.extern.slf4j.Slf4j;
import org.example.enumerators.UserState;
import org.example.exception.DataNotFoundException;
import org.example.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


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
        switch (currentUser.getUserState()){
            case SHARE_CONTACT -> {
                try {
                    execute(registrationHandler.handleContact(message,currentUser));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case SHARE_LOCATION -> {
                try {
                    execute(registrationHandler.handleLocation(message,currentUser));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            case MAIN_MENU,REGISTERED -> handleShopping(message);

        }
    }
    public void handleShopping(Message message){
        Long chatId = message.getChatId();
        String text = message.getText();
        if(Objects.equals(text,"\uD83D\uDED2Shopping") || (Objects.equals(text,"Shopping")) || (Objects.equals(text,"shopping"))){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("\t\t\tChoose one");
            sendMessage.setReplyMarkup(buttons.shoppingPage());
            SendMessage message1 = new SendMessage();
            message1.setChatId(chatId);
            message1.setText("");
            message1.setReplyMarkup(buttons.back());
            try {
                execute(sendMessage);
                execute(message1);
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
            SendMessage sendMessage = new SendMessage(chatId.toString(), String.format("Welcome back %s choose one!!!", user.getFirstName()));
            return sendMessage;
        }catch (DataNotFoundException e){
            log.info(e.getMessage(),chatId);
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
