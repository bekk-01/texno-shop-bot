package org.example.bot.product;

import org.example.bot.MyBot;
import org.example.model.Computer;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Objects;

public class Product extends MyBot {
    public void Computer(Message message){
        Long chatId = message.getChatId();
        String text = message.getText();
        if(Objects.equals(text,"Computer") || Objects.equals(text,"computer")){
            //computerService.add(new Computer(""))
        }
    }
}
