package org.example.bot.product;

import org.example.bot.MyBot;
import org.example.enumerators.ComputerState;
import org.example.enumerators.UserState;
import org.example.model.Computer;
import org.example.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;



public class CreateProduct extends MyBot {
    public void createComputer(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();

        if (Objects.equals(text, "\uD83D\uDCBBComputer") || text.equalsIgnoreCase("Computer")) {
            User user = userService.findByChatId(chatId);
            Computer computer1 = new Computer();
            computerService.add(computer1);
            user.setStartedProduct(computer1.getId());
            userService.update(user);
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Enter photo: ");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void createComputerFields(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();
        User user = userService.findByChatId(chatId);

        Computer computer = computerService.getById(user.getStartedProduct());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.hasPhoto()) {
            List<PhotoSize> photoSizes = message.getPhoto();
            String fileId = photoSizes.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            computer.setFileId(fileId);
            computerService.update(computer);
            user.setState(ComputerState.MODEL);
            userService.update(user);

            try {
                sendMessage.setText("Enter Model: ");
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
            if(user.getState() == ComputerState.MODEL) {
                user.setState(ComputerState.MODEL_BY);
                userService.update(user);
                computer.setModel(text);
                computerService.update(computer);
                sendMessage.setText("Enter ModelBy: ");
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ComputerState.MODEL_BY) {
                user.setState(ComputerState.RAM);
                userService.update(user);
                sendMessage.setText("Enter Ram: ");
                computer.setModelBy(text);
                computerService.update(computer);
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ComputerState.RAM){
                user.setState(ComputerState.CUP);
                userService.update(user);
                sendMessage.setText("Enter Cup: ");
                computer.setRam(text);
                computerService.update(computer);
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ComputerState.CUP) {
                user.setState(ComputerState.PRICE);
                userService.update(user);
                sendMessage.setText("Enter Price: ");
                computer.setCup(text);
                computerService.update(computer);
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ComputerState.PRICE){
                user.setState(ComputerState.COUNT);
                userService.update(user);
                sendMessage.setText("Enter Count: ");
                computer.setPrice(text);
                computerService.update(computer);
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ComputerState.COUNT){
                user.setUserState(UserState.SELL);
                userService.update(user);
                computer.setCount(Integer.parseInt(text));
                computerService.update(computer);
                sendMessage.setText("Successfully");
                sendMessage.setReplyMarkup(buttons.sellPage());
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

