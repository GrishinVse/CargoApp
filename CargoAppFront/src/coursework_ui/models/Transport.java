package coursework_ui.models;

import com.google.gson.Gson;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;
import java.util.Map;

public class Transport implements JSONSerialize{
    private final LongProperty id;
    private final StringProperty brand;
    private final StringProperty capacity;
    private final StringProperty carrying;
    private final StringProperty licence_plate;

    public Transport(){
        this(null, null, null, null);
    }

    public Transport(String brand, String capacity, String carrying, String licence_plate) {
        this.id = null;
        this.brand = new SimpleStringProperty(brand);
        this.capacity = new SimpleStringProperty(capacity);
        this.carrying = new SimpleStringProperty(carrying);
        this.licence_plate = new SimpleStringProperty(licence_plate);
    }

    public Transport(Long id, String brand, String capacity, String carrying, String licence_plate) {
        this.id = new SimpleLongProperty(id);
        this.brand = new SimpleStringProperty(brand);
        this.capacity = new SimpleStringProperty(capacity);
        this.carrying = new SimpleStringProperty(carrying);
        this.licence_plate = new SimpleStringProperty(licence_plate);
    }

    // Getters

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getCapacity() {
        return capacity.get();
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    public String getCarrying() {
        return carrying.get();
    }

    public StringProperty carryingProperty() {
        return carrying;
    }

    public String getLicence_plate() {
        return licence_plate.get();
    }

    public StringProperty licence_plateProperty() {
        return licence_plate;
    }

    // Setters

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public void setCapacity(String capacity) {
        this.capacity.set(capacity);
    }

    public void setCarrying(String carrying) {
        this.carrying.set(carrying);
    }

    public void setLicence_plate(String licence_plate) {
        this.licence_plate.set(licence_plate);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id.get()));
        map.put("brand", brand.get());
        map.put("capacity", capacity.get());
        map.put("carrying", carrying.get());
        map.put("licence_plate", licence_plate.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
