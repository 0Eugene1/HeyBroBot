package bookingbot;

import masterlist.Master;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import repositories.MastersRepository;
import services.MastersService;

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


        if (message.trim().matches("[1-99]")) {         // todo ответ на какой вопрос?
            int staffId = mastersService.getStaffIdByIndex(message);
            return yClientsService.getCloserAvailableSeance(COMPANY_ID, staffId);
        }

        switch (message.trim().toLowerCase()) {
            case "/start":
                return "Добро пожаловать! Я помогу вам записаться на стрижку, пожалуйста выберите подходящую услугу.";
            case "/book":
                return yClientsService.getMasterList(COMPANY_ID);
            default:
                return "Извините, я не понимаю команду. Попробуйте /start.";
        }
    }
}
