package coursework_ui.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import coursework_ui.models.Transport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * Данный класс обращается к серверу по протоколу HTTP
 */
public class RequestManager {

    private static final String ServerURL = "http://localhost:8090/cargo_app/";

    public ObservableList<Transport> getTransports() throws IOException {
        ObservableList<Transport> transportData = FXCollections.observableArrayList();
        String value = HttpManager.GetRequest(ServerURL + "transports");
        if (value.equals("null")){
            System.out.println("Have returned Null! Something went wrong");
            System.out.println(value);
            return null;}
        else {
            JsonArray jsonResult = new JsonParser().parse(value).getAsJsonArray();

            for (int i = 0; i < jsonResult.size(); i++) {
                JsonObject currentTransport = jsonResult.get(i).getAsJsonObject();

                Long id = currentTransport.get("id").getAsLong();
                String brand = currentTransport.get("brand").getAsString();
                String capacity = currentTransport.get("capacity").getAsString();
                String carrying = currentTransport.get("carrying").getAsString();
                String licence_plate = currentTransport.get("licence_plate").getAsString();
                Transport resultTransport = new Transport(id, brand, capacity, carrying, licence_plate);

                transportData.add(resultTransport);
            }
        }
        return transportData;
    }

    public Boolean deleteTransport(Transport transport) {
        Long id = transport.getId();
        if (id == null)
            return false;

        return HttpManager.DeleteRequest(ServerURL + "transports/" + id);
    }

    public void updateTransport(Transport transport){
        System.out.println("JSON on updated Transport:\n" + transport.toJson());
        HttpManager.PutRequest(ServerURL + "transports/" + transport.getId(), transport.toJson());
    }

}
