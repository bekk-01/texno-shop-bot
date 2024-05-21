package org.example.bot.product;

import org.example.bot.MyBot;
import org.example.enumerators.ProductState;
import org.example.enumerators.UserState;
import org.example.model.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class CreateProduct extends MyBot {
    public void createComputer(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();

        if (Objects.equals(text, "\uD83D\uDCBBComputer") || text.equalsIgnoreCase("Computer")) {
            User user = userService.findByChatId(chatId);
            user.setUserState(UserState.SELL_COMPUTER);
            userService.update(user);
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
            user.setState(ProductState.MODEL);
            userService.update(user);

            try {
                sendMessage.setText("Enter Model: ");
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
            if(user.getState() == ProductState.MODEL) {
                user.setState(ProductState.MODEL_BY);
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
            if(user.getState() == ProductState.MODEL_BY) {
                user.setState(ProductState.RAM);
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
            if(user.getState() == ProductState.RAM){
                user.setState(ProductState.CUP);
                userService.update(user);
                sendMessage.setText("Enter CPU: ");
                computer.setRam(text);
                computerService.update(computer);
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if(user.getState() == ProductState.CUP) {
                user.setState(ProductState.PRICE);
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
            if(user.getState() == ProductState.PRICE){
                user.setState(ProductState.COUNT);
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
            if(user.getState() == ProductState.COUNT){
                computer.setCount(text);
                computerService.update(computer);
                user.setState(ProductState.FINISH);
                user.setUserState(UserState.SELL);
                userService.update(user);
                sendMessage.setText("Successfully added");
                sendMessage.setReplyMarkup(buttons.sellPage());
                try {
                    execute(sendMessage);
                    return;
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    public void createPhone(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();

        if (Objects.equals(text, "\uD83D\uDCF1Phone") || text.equalsIgnoreCase("Phone")) {
            User user = userService.findByChatId(chatId);
            Phone phone1 = new Phone();
            phoneService.add(phone1);
            user.setStartedProduct(phone1.getId());
            user.setUserState(UserState.SELL_PHONE);
            userService.update(user);
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Enter photo: ");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void createPhoneFields(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();
        User user = userService.findByChatId(chatId);
        Phone phone = phoneService.getById(user.getStartedProduct());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.hasPhoto()) {
            List<PhotoSize> photoSizes = message.getPhoto();
            String fileId = photoSizes.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            phone.setFileId(fileId);
            phoneService.update(phone);
            user.setState(ProductState.MODEL);
            userService.update(user);

            try {
                sendMessage.setText("Enter Model: ");
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.MODEL) {
            user.setState(ProductState.MODEL_BY);
            userService.update(user);
            phone.setModel(text);
            phoneService.update(phone);
            sendMessage.setText("Enter ModelBy: ");
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.MODEL_BY) {
            user.setState(ProductState.RAM);
            userService.update(user);
            sendMessage.setText("Enter Battery: ");
            phone.setModelBy(text);
            phoneService.update(phone);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.RAM){
            user.setState(ProductState.CUP);
            userService.update(user);
            sendMessage.setText("Enter DisplayHrz: ");
            phone.setBattery(text);
            phoneService.update(phone);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.CUP) {
            user.setState(ProductState.PRICE);
            userService.update(user);
            sendMessage.setText("Enter Price: ");
            phone.setDisplayHrz(text);
            phoneService.update(phone);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.PRICE){
            user.setState(ProductState.COUNT);
            userService.update(user);
            sendMessage.setText("Enter Count: ");
            phone.setPrice(text);
            phoneService.update(phone);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.COUNT){
            phone.setCount(text);
            phoneService.update(phone);
            user.setState(ProductState.FINISH);
            user.setUserState(UserState.SELL);
            userService.update(user);
            sendMessage.setText("Successfully added");
            sendMessage.setReplyMarkup(buttons.sellPage());
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void createTv(CallbackQuery callbackQuery) {
        String text = callbackQuery.getData();
        Long chatId = callbackQuery.getMessage().getChatId();
        if (Objects.equals(text, "\uD83D\uDCFATV") || text.equalsIgnoreCase("Tv")) {
            User user = userService.findByChatId(chatId);
            Tv tv1 = new Tv();
            tvService.add(tv1);
            user.setStartedProduct(tv1.getId());
            user.setUserState(UserState.SELL_TV);
            userService.update(user);
            SendMessage sendMessage = new SendMessage(chatId.toString(), "Enter photo: ");
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void createTvFields(Message message) {
        Long chatId = message.getChatId();
        String text = message.getText();
        User user = userService.findByChatId(chatId);
        Tv tv = tvService.getById(user.getStartedProduct());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        if (message.hasPhoto()) {
            List<PhotoSize> photoSizes = message.getPhoto();
            String fileId = photoSizes.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null).getFileId();
            tv.setFileId(fileId);
            tvService.update(tv);
            user.setState(ProductState.MODEL);
            userService.update(user);

            try {
                sendMessage.setText("Enter Model: ");
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.MODEL) {
            user.setState(ProductState.MODEL_BY);
            userService.update(user);
            tv.setModel(text);
            tvService.update(tv);
            sendMessage.setText("Enter ModelBy: ");
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.MODEL_BY) {
            user.setState(ProductState.RAM);
            userService.update(user);
            sendMessage.setText("Enter Dmu: ");
            tv.setModelBy(text);
            tvService.update(tv);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.RAM){
            user.setState(ProductState.CUP);
            userService.update(user);
            sendMessage.setText("Enter SmartOrNoSmart: ");
            tv.setDmu(text);
            tvService.update(tv);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.CUP) {
            user.setState(ProductState.PRICE);
            userService.update(user);
            sendMessage.setText("Enter Price: ");
            tv.setSmart(text);
            tvService.update(tv);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.PRICE){
            user.setState(ProductState.COUNT);
            userService.update(user);
            sendMessage.setText("Enter Count: ");
            tv.setPrice(text);
            tvService.update(tv);
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if(user.getState() == ProductState.COUNT){
            tv.setCount(text);
            tvService.update(tv);
            user.setState(ProductState.FINISH);
            user.setUserState(UserState.SELL);
            userService.update(user);
            sendMessage.setText("Successfully added");
            sendMessage.setReplyMarkup(buttons.sellPage());
            try {
                execute(sendMessage);
                return;
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void searchCallback(CallbackQuery callbackQuery){
        Long chatId = callbackQuery.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Searching By");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void search(Message message){
        Long chatId = message.getChatId();
        String text = message.getText();
        ArrayList<Phone> phones = phoneService.searchByTitle(text);
        ArrayList<Tv> tvs = tvService.searchByTitle(text);
        ArrayList<Computer> computers = computerService.searchByTitle(text);
        SendMessage sendMessage = new SendMessage(chatId.toString(),"Would you like to buy↘️↘️");
        if(tvs != null) {
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
        if(phones != null) {
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
        if(computers != null) {
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
        User user = userService.findByChatId(chatId);
        user.setUserState(UserState.SHOPPING);
        userService.update(user);

    }


}

