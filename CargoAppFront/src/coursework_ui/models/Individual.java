package coursework_ui.models;

import com.google.gson.Gson;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Клиент (физичекое лицо)
 */
public class Individual implements JSONSerialize{
    private final LongProperty id;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty firstName;
    private final StringProperty lastName;

    public Individual(){
        this(null, null, null, null, null);
    }

    public Individual(Long id, String email, String phone, String firstName, String lastName) {
        this.id = new SimpleLongProperty(id);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
    }

    /**
     * Эта часть с геттерами для данного класса
     */

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    /**
     * Эта часть с сеттерами для данного класса
     */

    public void setId(Long id) {
        this.id.set(id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * Преобразует экземпляр класса в JSON
     * @return Json запись экземпляра класса Individual
     */
    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        if (id == null){
            System.out.printf("ID of new Individual Client ", this.getClass(), " is NULL!");
            map.put("id", "");
        } else {
            System.out.printf("ID of new Individual Client ", this.getClass(), " is ", id.get());
            map.put("id", String.valueOf(id.get()));
        }

        map.put("email", email.get());
        map.put("phone", phone.get());
        map.put("first_name", firstName.get());
        map.put("last_name", lastName.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
