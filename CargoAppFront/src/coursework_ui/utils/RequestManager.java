package coursework_ui.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import coursework_ui.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Date;

/**
 * Данный класс обращается к серверу по протоколу HTTP
 */
public class RequestManager {
    /**
     * URL ссылка на сервер
     */
    private static final String ServerURL = "http://localhost:8090/cargo_app/";

    /**
     * Обращается к Базе данных по GET запросу и возвращает значения транспортных средств
     * @return Список всех транспортных средств из БД
     * @throws IOException
     */
    public ObservableList<Transport> getTransports() throws IOException {
        ObservableList<Transport> transportData = FXCollections.observableArrayList();
        String value = HttpManager.GetRequest(ServerURL + "transports");
        if (value.equals("null")){
            System.out.println("Have returned Null! Something went wrong");
            System.out.println(value);
            return null;
        } else {
            JsonArray jsonResult = new JsonParser().parse(value).getAsJsonArray();

            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentTransport = jsonResult.get(i).getAsJsonObject();

                Transport resultTransport = parseTransport(currentTransport);

                transportData.add(resultTransport);
            }
        }
        return transportData;
    }

    /**
     * Преобразует JSON запись экземпляра в Java класс
     * @param transportJson JSON запись объекта
     * @return экземпляр класса Transport
     */
    public Transport parseTransport(JsonObject transportJson){
        Long id = transportJson.get("id").getAsLong();
        String brand = transportJson.get("brand").getAsString();
        String capacity = transportJson.get("capacity").getAsString();
        String carrying = transportJson.get("carrying").getAsString();
        String licence_plate = transportJson.get("licence_plate").getAsString();
        return new Transport(id, brand, capacity, carrying, licence_plate);
    }

    /**
     * Создает экземпляр класса Transport
     * @param transport запись для создания
     */
    public void createTransport(Transport transport){
        System.out.println("JSON of new Transport:\n" + transport.toJson());
        HttpManager.PostRequest(ServerURL + "transports", transport.toJson());
    }

    /**
     * Изменяет/добавляет экземпляр класса Transport
     * @param transport запись для изменения
     */
    public void updateTransport(Transport transport){
        System.out.println("JSON on updated Transport:\n" + transport.toJson());
        HttpManager.PutRequest(ServerURL + "transports/" + transport.getId(), transport.toJson());
    }

    /**
     * Удаляет экземпляр класса Transport
     * @param transport запись для удаления
     * @return ответ от сервера (200) - успешное удаление
     */
    public Boolean deleteTransport(Transport transport) {
        Long id = transport.getId();
        if (id == null)
            return false;

        return HttpManager.DeleteRequest(ServerURL + "transports/" + id);
    }

    /**
     * Обращается к Базе данных по GET запросу и возвращает значения заказов
     * @return Список всех заказов из БД
     * @throws IOException
     */
    public ObservableList<OrderList> getOrderLists() throws IOException {
        ObservableList<OrderList> orderListsData = FXCollections.observableArrayList();
        String value = HttpManager.GetRequest(ServerURL + "order_lists");
        if (value.equals("null")){
            System.out.println("Have returned Null! Something went wrong");
            System.out.println(value);
            return null;
        } else {
            JsonArray jsonResult = new JsonParser().parse(value).getAsJsonArray();

            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentOrderList = jsonResult.get(i).getAsJsonObject();

                System.out.println(currentOrderList);

                Long id = currentOrderList.get("id").getAsLong();
                String startAddress = currentOrderList.get("start_address").getAsString();
                String otherAddress = currentOrderList.get("other_address").getAsString();
                String endAddress = currentOrderList.get("end_address").getAsString();
                String orderType = currentOrderList.get("order_type").getAsString();
                String description = currentOrderList.get("description").getAsString();

                JsonObject transportJson = currentOrderList.get("transport").getAsJsonObject();
                Transport connTransport = parseTransport(transportJson);

                if (currentOrderList.get("employee").toString().equals("null")){
                    System.out.println("Our Employee is NOT Defined!");
                } else {
                    JsonObject employeeJson = currentOrderList.get("employee").getAsJsonObject();
                    Employee connEmployee = parseEmployee(employeeJson);
                }

                /**
                 * Эта часть кода отвечает за выборку типа клиента
                 */

                Boolean isIndividual = !currentOrderList.get("individual").toString().equals("null");
                Boolean isCorporate = !currentOrderList.get("corporate").toString().equals("null");

                Object resultOrderList = null;

                if (isIndividual & !isCorporate){
                    System.out.println("Our Client is Individual");
                    JsonObject individualJson = currentOrderList.get("individual").getAsJsonObject();
                    Individual connIndividual = parseIndividual(individualJson);
                    // 1) corporateId = null & individualId = connClient
                    resultOrderList = new OrderList(id, startAddress, otherAddress, endAddress,
                            orderType, description, connTransport, connIndividual);
                }

                if (!isIndividual & isCorporate) {
                    System.out.println("Our Client is Corporate");
                    JsonObject corporateJson = currentOrderList.get("corporate").getAsJsonObject();
                    //Object connClient = parseCorporate(corporateJson);
                    // 2) corporateId = connClient & individualId = null
                }

                if (!isIndividual & !isCorporate){
                    System.out.println("Our Client is NOT Defined!");
                    Object connClient = null;
                }
                /*
                try {
                    JsonObject individualJson = currentOrderList.get("individual").getAsJsonObject();
                    System.out.println(individualJson);
                } catch (IllegalStateException e){
                    e.printStackTrace();
                }

                 */

                /* Разные создания OrderList на основе клиента
                1) corporateId = null & individualId = connClient
                2) corporateId = connClient & individualId = null
                3) corporateId = null & individualId = null
                 */
                //OrderList resultOrderList = new OrderList(id, startAddress, otherAddress, endAddress, orderType, description, connTransport);

                orderListsData.add((OrderList) resultOrderList);
            }
        }
        return orderListsData;
    }

    /**
     * Обращается к Базе данных по GET запросу и возвращает значения профессий
     * @return Список всех профессий из БД
     * @throws IOException
     */
    public ObservableList<Job> getJobs() throws IOException {
        ObservableList<Job> jobData = FXCollections.observableArrayList();
        String value = HttpManager.GetRequest(ServerURL + "jobs");
        if (value.equals("null")) {
            System.out.println("Have returned Null! Something went wrong");
            System.out.println(value);
            return null;
        } else {
            JsonArray jsonResult = new JsonParser().parse(value).getAsJsonArray();

            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentJob = jsonResult.get(i).getAsJsonObject();

                Job job = parseJob(currentJob);

                jobData.add(job);
            }
            return jobData;
        }
    }

    /**
     * Преобразует JSON запись экземпляра в Java класс
     * @param jobJson JSON запись объекта
     * @return экземпляр класса Job
     */
    public Job parseJob(JsonObject jobJson){
        Long id = jobJson.get("id").getAsLong();
        String title = jobJson.get("title").getAsString();
        String minSalary = jobJson.get("min_salary").getAsString();
        return new Job(id, title, minSalary);
    }

    /**
     * Преобразует JSON запись экземпляра в Java класс
     * @param individualJson JSON запись объекта
     * @return экземпляр класса Individual
     */
    public Individual parseIndividual(JsonObject individualJson){
        Long id = individualJson.get("id").getAsLong();
        String email = individualJson.get("email").getAsString();
        String phone = individualJson.get("phone").getAsString();
        String firstName = individualJson.get("first_name").getAsString();
        String lastName = individualJson.get("last_name").getAsString();
        return new Individual(id, email, phone, firstName, lastName);
    }

    public Employee parseEmployee(JsonObject employeeJson){
        Long id = employeeJson.get("id").getAsLong();
        String firstName = employeeJson.get("first_name").getAsString();
        String lastName = employeeJson.get("last_name").getAsString();
        Date hireDate = Date.valueOf(employeeJson.get("hire_date").getAsString());
        Float rating = employeeJson.get("rating").getAsFloat();

        Job job = parseJob(employeeJson.get("job_id").getAsJsonObject());

        return new Employee(id, firstName, lastName, hireDate, rating, job);
    }
}
