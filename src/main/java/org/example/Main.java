package org.example;

import bookingbot.HaircutBookingBot;
import bookingbot.YClientsService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import repositories.MastersRepository;
import services.MastersService;

public class Main {
    public static void main(String[] args) {

        MastersRepository mastersRepository = new MastersRepository();
        MastersService mastersService = new MastersService(mastersRepository);
        YClientsService yClientsService = new YClientsService(mastersService);

//       yClientsService.getMasterList(86034);
//       yClietnsService.bookService(86034);
//        yClietnsService.getSchedule(86034);
//        yClietnsService.getCloserAvailableSeance(86034, 3382051);
//        yClietnsService.getSchedule(86034);
        yClientsService.getSchedule(86034, "2024-09-16", "2024-9-16");

//        System.out.println(yClietnsService.getAvailableSeance(86034, 3382051, "2024-09-12"));

//        try {
//            // создаёт объект TelegramBotsApi, который необходим для регистрации и управления ботами в Telegram.
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class); //DefaultBotSession.class Это стандартная реализация сессии для работы с Telegram API.
//            botsApi.registerBot(new HaircutBookingBot()); //Этот метод регистрирует бот
//        } catch (TelegramApiException e) { // TelegramApiException — это исключение, связанное с ошибками API Telegram
//            e.printStackTrace();
//        }
    }
    }
