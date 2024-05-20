package org.example.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Buttons extends MyBot{
    public ReplyKeyboardMarkup requestContact() {
        KeyboardButton button = new KeyboardButton("\uD83D\uDCDEShare your number");
        button.setRequestContact(true);//o'zini raqamini yuboradi
        ReplyKeyboardMarkup replyKeyboardMarkup =
                new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(button))));
        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni kichik o'lchamda chiqaradi
        return replyKeyboardMarkup;

    }

    public ReplyKeyboardMarkup requestLocation() {
        KeyboardButton button = new KeyboardButton("\uD83D\uDCCDShare your location");
        button.setRequestLocation(true);
        ReplyKeyboardMarkup replyKeyboardMarkup =
                new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(button))));
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public ReplyKeyboardMarkup homePage(){
        List<KeyboardRow> keyboards = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("\uD83D\uDED2Shopping");
        row.add("\uD83D\uDED2Sell");
        keyboards.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboards);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public ReplyKeyboardMarkup back(){
        List<KeyboardRow> keyboards = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("⬅\uFE0FBack");
        keyboards.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboards);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
    public InlineKeyboardMarkup shoppingPage(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row =  new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("\uD83D\uDCBBComputer");
        button.setCallbackData("Computer");
        row.add(button);
        button = new InlineKeyboardButton("\uD83D\uDCF1Phone");
        button.setCallbackData("Phone");
        row.add(button);
        button = new InlineKeyboardButton("\uD83D\uDCFATV");
        button.setCallbackData("TV");
        row.add(button);
        rows.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton("\uD83D\uDD0ESearch");
        button.setCallbackData("Search");
        row.add(button);

        button = new InlineKeyboardButton("\uD83D\uDDD1Buckets");
        button.setCallbackData("Buckets");
        row.add(button);

        button = new InlineKeyboardButton("\uD83D\uDCC3History");
        button.setCallbackData("History");
        row.add(button);

        rows.add(row);

        return new InlineKeyboardMarkup(rows);
    }
    public InlineKeyboardMarkup bucketsAdd(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("Create Buckets");
        button.setCallbackData("Create Buckets");
        row.add(button);
        rows.add(row);



        return new InlineKeyboardMarkup(rows);
    }
    public InlineKeyboardMarkup sellPage(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("\uD83D\uDCBBComputer");
        button.setCallbackData("Computer");
        row.add(button);

        button = new InlineKeyboardButton("\uD83D\uDCF1Phone");
        button.setCallbackData("Phone");
        row.add(button);

        button = new InlineKeyboardButton("\uD83D\uDCFATV");
        button.setCallbackData("Tv");
        row.add(button);

        rows.add(row);

        return new InlineKeyboardMarkup(rows);

    }
}