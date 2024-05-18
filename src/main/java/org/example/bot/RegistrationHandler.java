package org.example.bot;

import org.example.enumerators.UserState;
import org.example.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.List;
import java.util.Objects;

import static org.example.bot.MyBot.buttons;
import static org.example.bot.MyBot.userService;

public class RegistrationHandler {
    public SendMessage handleContact(Message message, User currentUser){
        Long chatId = message.getChatId();
        if(message.hasContact()){
            Contact contact = message.getContact();
            if(!Objects.equals(contact.getUserId(),chatId)){
                return new SendMessage(chatId.toString(),"Please share your own number");
            }
            currentUser.setPhoneNumber(contact.getPhoneNumber());
            currentUser.setUserState(UserState.SHARE_LOCATION);
            userService.update(currentUser);
            SendMessage sendMessage = new SendMessage(chatId.toString(),"Thank you, please share your location");
            sendMessage.setReplyMarkup(buttons.requestLocation());
            return sendMessage;
        }
        return new SendMessage(chatId.toString(),"Please send your contact");
    }
    public SendMessage handleLocation(Message message,User currentUser){
        Long chatId = message.getChatId();
        if(message.hasLocation()){
            Location location = message.getLocation();
            currentUser.setLocation(location);
            currentUser.setUserState(UserState.REGISTERED);
            userService.update(currentUser);
            SendMessage sendMessage = new SendMessage(chatId.toString(),"Thank you");
            sendMessage.setReplyMarkup(buttons.homePage());
            return sendMessage;
        }
        return new SendMessage(chatId.toString(),"Please send your location!!!");
    }
}
