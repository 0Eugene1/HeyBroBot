package bookingbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class HaircutBookingBot extends TelegramLongPollingBot {

    private YClietnsService yClietnsService;
    private final int COMPANY_ID = 86034;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
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
        switch (message.toLowerCase()) {
            case "/start" :
                return "Добро пожаловать! Я помогу вам записаться на стрижку, пожалуйста выберите подходящую услугу.";
            case "/book" :
                yClietnsService.getMasterList(COMPANY_ID);
            default:
                return "Извините, я не понимаю команду. Попробуйте /start.";
        }
    }
}
