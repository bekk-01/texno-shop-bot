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

        button = new InlineKeyboardButton("\uD83D\uDDC2Download");
        button.setCallbackData("Download");
        row.add(button);

        rows.add(row);

        return new InlineKeyboardMarkup(rows);
    }
    public InlineKeyboardMarkup bucketsAdd(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton(" - ");
        button.setCallbackData("-");
        row.add(button);

        button = new InlineKeyboardButton("0");
        button.setCallbackData("0");
        row.add(button);

        button = new InlineKeyboardButton("+");
        button.setCallbackData("+");
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

        button = new InlineKeyboardButton("\uD83D\uDDC2Download");
        button.setCallbackData("Download");
        row.add(button);

        rows.add(row);

        return new InlineKeyboardMarkup(rows);

    }
    public InlineKeyboardMarkup downloadButton(){
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton("Excel");
        button.setCallbackData("Excel");
        row.add(button);

        button = new InlineKeyboardButton("Pdf");
        button.setCallbackData("Pdf");
        row.add(button);

        button = new InlineKeyboardButton("Word");
        button.setCallbackData("Word");
        row.add(button);

        button = new InlineKeyboardButton("Email");
        button.setCallbackData("Email");
        row.add(button);

        rows.add(row);
        return new InlineKeyboardMarkup(rows);
    }
}