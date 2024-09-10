package bookingbot;

import repositories.MastersRepository;
import responsecloseravailableseances.Data;
import com.google.gson.Gson;
import masterlist.Master;
import masterlist.ResponseMasterList;
import responseavailableseances.ResponseAvailableSeances;
import responseavailableseances.SeancesData;
import responsecloseravailableseances.ResponseSeanceDate;
import schedule.ResponseSchedule;
import schedule.ScheduleInfo;
import services.MastersService;
import services.ResponseServices;
import services.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class YClientsService {

    private final Gson gson = new Gson();// Используем Gson для работы с JSO
    private MastersService mastersService;

    public YClientsService(MastersService mastersService) {
        this.mastersService = mastersService;
    }

    // ПОЛУЧИТЬ СПИСОК СОТРУДНИКОВ ДОСТУПНЫХ ДЛЯ БРОНИРОВАНИЯ
    public String getMasterList(int companyId) {

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.yclients.com/api/v1/book_staff/" + companyId))
                    .header("Accept", "application/vnd.yclients.v2+json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "j8p3aadsnhaexg4wp8k9")
                    .build();

            // Отправляем запрос и получаем ответ
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Выводим полученный JSON для проверки
            String json = response.body();
            System.out.println(json);  // Можно удалить после отладки

            //  Преобразуем JSON в объект MasterList
            ResponseMasterList responseMasterList = gson.fromJson(json, ResponseMasterList.class);
            mastersService.clearAndAddMasters(responseMasterList.getData());


            // todo toString() in MastersRepository



            return mastersService.getMastersChoice();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // ПОЛУЧИТЬ СПИСОК БЛИЖАЙШИХ ДОСТУПНЫХ СЕАНСОВ
    public String getCloserAvailableSeance(int companyId, int staff_id) {

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.yclients.com/api/v1/book_staff_seances/" + companyId + "/" + staff_id))
                    .header("Accept", "application/vnd.yclients.v2+json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "j8p3aadsnhaexg4wp8k9") //todo нужно ли?
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            ResponseSeanceDate dataJson = gson.fromJson(json, ResponseSeanceDate.class);  //todo так ли??
            List<Data> responseSeances = dataJson.getData();    //??

            StringBuilder builder = new StringBuilder();

            for (Data responseSeance : responseSeances) {
                builder.append("Информация о ближайшем сеансе: ").append(responseSeance)
                        .append("\n")
                        .append(responseSeance.getSeances());       // fixme long line

            }
            return builder.toString();
//            }else{
//            return "Не удалось получить список сеансов";
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // ПОЛУЧАЕМ УНИКАЛЬНЫЙ ID МАСТЕРА fixme как с этим работать и нужно ли?         StringBuilder instead of System.out.println()
//    public void getMasterId(int companyId) { // нужно ли?
//
//        MasterList masterList = getMasterList(companyId);
//        if (masterList.isSuccess()) {
//            for (Master master : masterList.getData()) {
//                System.out.println("ID мастера: " + master.getId() + ", Имя: " + master.getName());
//            }
//        } else {
//            System.out.println("Не удалось получить список мастеров");
//        }
//    }

    // СПИСОК УСЛУГ ДОСТУПНЫХ ДЛЯ БРОНИРОВАНИЯ
    public String bookService(int companyId) {

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.yclients.com/api/v1/book_services/" + companyId))
                    .header("Accept", "application/vnd.yclients.v2+json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "j8p3aadsnhaexg4wp8k9")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            ResponseServices responseServices = gson.fromJson(json, ResponseServices.class);
            List<Services> servicesList = responseServices.getData().getServices();

            StringBuilder builder = new StringBuilder();
            int counter = 1;
            for (Services service : servicesList) {
                builder.append("(")
                        .append(counter)
                        .append(") ")
                        .append(service.getTitle()).append(" - ")
                        .append(service.getPrice_max()).append("\n");
                counter++;
            }
            builder.append("Выберите услугу");

            return builder.toString();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //ПОЛУЧИТЬ СПИСОК СЕАНСОВ !ДОСТУПНЫХ! ДЛЯ БРОНИРОВАНИЯ
    public String getAvailableSeance(int companyId, int staffId, String date) {

        try(HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.yclients.com/api/v1/book_times/" + companyId + "/" + staffId + "/" + date))
                    .header("Accept", "application/vnd.yclients.v2+json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "j8p3aadsnhaexg4wp8k9")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            ResponseAvailableSeances seancesData = gson.fromJson(json, ResponseAvailableSeances.class);
            List<SeancesData> availableSeances = seancesData.getData();

            StringBuilder builder = new StringBuilder();

            int counter = 1;
            for (SeancesData seances : availableSeances) {
                builder.append("(")
                        .append(counter)
                        .append(") ")
                        .append(seances)  //todo просто getData()?
                        .append("\n");
                counter++;
            }
            builder.append("Выберите индекс желаемого сеанса");     // TODO no sessions for today

            return builder.toString();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //ПОЛУЧЕНИЕ ГРАФИКОВ РАБОТЫ СОТРУДНИКОВ
    public String getSchedule(int companyId) {  //fixme КАК РАБОТАТЬ С ДАТАМИ?

        try (HttpClient client = HttpClient.newHttpClient()) {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.yclients.com/api/v1/company/" + companyId + "/staff/schedule"))
                    .header("Accept", "application/vnd.yclients.v2+json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "j8p3aadsnhaexg4wp8k9")  //todo НЕ УКАЗАН ИДЕНТЕФИКАТОР, НО ОН УКАЗАН
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);

            ResponseSchedule scheduleInfo = gson.fromJson(json, ResponseSchedule.class);

//            // Проверяем, успешен ли запрос
//            if (!scheduleInfo.isSuccess()) {
//                // Если неуспешен, выводим сообщение об ошибке из поля meta
//                String errorMessage = scheduleInfo.getMeta().toString();
//                return "Ошибка при получении расписания: " + errorMessage;
//            }

            List<ScheduleInfo> scheduleInfos = scheduleInfo.getData();

            StringBuilder sb = new StringBuilder();
            int counter = 1;
            for (ScheduleInfo scheduleInfoItem : scheduleInfos) { //fixme Cannot invoke "java.util.List.iterator()" because "scheduleInfos" is null
                sb.append("(")
                        .append(counter)
                        .append(") Staff ID: ")
                        .append(scheduleInfoItem.getStaff_id())
                        .append(", Date: ")
                        .append(scheduleInfoItem.getDate())
                        .append("\n");

                counter++;
            }

            return sb.toString();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

