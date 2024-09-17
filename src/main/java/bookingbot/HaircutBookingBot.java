package bookingbot;

import masterlist.Master;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repositories.MastersRepository;
import services.MastersService;

import java.util.ArrayList;
import java.util.List;

public class HaircutBookingBot extends TelegramLongPollingBot {

    private YClientsService yClientsService;
    private MastersService mastersService;
    private final int COMPANY_ID = 86034;

    public HaircutBookingBot() {
        mastersService = new MastersService(new MastersRepository());
        yClientsService = new YClientsService(mastersService);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callbackData) {
                case "getMasterList":
                    String masterList = yClientsService.getMasterList(COMPANY_ID);
                    sendMessage(chatId, masterList);
                    break;
                case "bookService" :
                    String bookService = yClientsService.bookService(COMPANY_ID);
                    sendMessage(chatId, bookService);
                    break;
//                case "getCloserAvailableSeance":
//                    mastersService.getStaffIdByIndex() //TODO
            }
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            String recivedText = update.getMessage().getText();
            String responseText = handleUserMessage(recivedText);
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(responseText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "Hey, Bro!";
    }

    @Override
    public String getBotToken() {
        return "6749365316:AAG0qCA3_LTrzKAsNMp6hK4i-ifnSC0a5m8";
    }

    private String handleUserMessage(String message) {

//        if (message.trim().matches("[1-99]")) {         // todo ответ на какой вопрос?
//            int staffId = mastersService.getStaffIdByIndex(message);
//            return yClientsService.getCloserAvailableSeance(COMPANY_ID, staffId);
//        }

        switch (message.trim().toLowerCase()) {
            case "/start":
                return "Добро пожаловать! Я помогу вам записаться на стрижку, пожалуйста выберите подходящую услугу.";
            case "/book":
                return yClientsService.getMasterList(COMPANY_ID);
            default:
                return "Извините, я не понимаю команду. Попробуйте /start.";
        }
    }

    private void sendInlineKeyboard(long chatId) {
        // Creating new keyboard
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        // Creating 1 button
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Выбрать мастера");
        button1.setCallbackData("getMasterList");

        //Creating 2 button
        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Ближайшие доступные сеансы");
        button2.setCallbackData("getCloserAvailableSeance");

        //Creating 3 button
        InlineKeyboardButton button3 = new InlineKeyboardButton();
        button3.setText("Услуги доступные для бронирования");
        button3.setCallbackData("bookService");

        //Creating rows with buttons
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(button1);
        row1.add(button2);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(button3);

        //Adding rows in keyboard
        rows.add(row1);
        rows.add(row2);

        //Upload keyboard
        markup.setKeyboard(rows);

        //Sending messages from keyboard
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Выберите действие:");
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
