package org.example.bot.downloading;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.example.bot.MyBot;
import org.example.model.Phone;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Downloading extends MyBot {
    public String generateExelFile() {
        List<Phone> phones = phoneService.getAll();
        String fileName = "src/main/resources/send/phone.xlsx";
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Phone");
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Model","ModelBy","Price","Count","Battery","DisplayHrz"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            int rowNum = 1;
            for (Phone phone : phones) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(phone.getModel());
                row.createCell(1).setCellValue(phone.getModelBy());
                row.createCell(2).setCellValue(phone.getPrice());
                row.createCell(3).setCellValue(phone.getCount());
                row.createCell(4).setCellValue(phone.getBattery());
                row.createCell(5).setCellValue(phone.getDisplayHrz());
                try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
                    workbook.write(fileOutputStream);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
    public void sendExelFile(Long chatId, String fileName) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(new java.io.File(fileName)));
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public void handleExelFile(Long chatId) {
        String fileName = generateExelFile();
        sendExelFile(chatId, fileName);
        SendMessage sendMessage = new SendMessage(chatId.toString(), "Send successfully");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private String generatePhoneWordFile() {
        List<Phone> phones = phoneService.getAll();
        String fileName = "src/main/resources/send/phone.docx";
        try (XWPFDocument document = new XWPFDocument()) {
            for (Phone phone : phones) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText("Model: " + phone.getModel());
                run.addBreak();
                run.setText("ModelBy: " + phone.getModelBy());
                run.addBreak();
                run.setText("Battery: " + phone.getBattery());
                run.addBreak();
                run.setText("Count: " + phone.getCount());
                run.addBreak();
                run.setText("Price: " + phone.getPrice());
                run.addBreak();
                run.setText("DisplayHrz: " + phone.getDisplayHrz());
                run.addBreak();
                run.addBreak();
                try (FileOutputStream out = new FileOutputStream(fileName)) {
                    document.write(out);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
    private void sendWordFile(Long chatId, String filePath) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId.toString());
        sendDocument.setDocument(new InputFile(new java.io.File(filePath)));

        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void handleWordFile(Long chatId) {
        String filePath = generatePhoneWordFile();
        sendWordFile(chatId, filePath);
        SendMessage sendMessage = new SendMessage(chatId.toString(), "Send successfully");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
