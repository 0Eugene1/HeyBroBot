package org.example;

import bookingbot.HaircutBookingBot;
import bookingbot.YClietnsService;

public class Main {
    public static void main(String[] args) {

       YClietnsService yClietnsService= new YClietnsService();

//       yClietnsService.getMasterList(86034);
//       yClietnsService.bookService(86034);
        yClietnsService.getSchedule(86034, "2024-09-01", "2024-09-08");

    }
}