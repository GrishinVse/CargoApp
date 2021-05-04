package coursework_ui.models;

import com.google.gson.Gson;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Корпоративный клиент (юридическое лицо)
 */
public class Corporate implements JSONSerialize{
    private final LongProperty id;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty companyName;
    private final StringProperty legalAddress;

    public Corporate(){
        this(null, null, null, null, null);
    }

    public Corporate(Long id, String email, String phone, String companyName, String legalAddress) {
        this.id = new SimpleLongProperty(id);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.companyName = new SimpleStringProperty(companyName);
        this.legalAddress = new SimpleStringProperty(legalAddress);
    }

    /**
     * Эта часть с геттерами для данного класса
     */

    public long getId() {
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

    public String getCompanyName() {
        return companyName.get();
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public String getLegalAddress() {
        return legalAddress.get();
    }

    public StringProperty legalAddressProperty() {
        return legalAddress;
    }

    /**
     * Эта часть с сеттерами для данного класса
     */

    public void setId(long id) {
        this.id.set(id);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress.set(legalAddress);
    }

    /**
     * Преобразует экземпляр класса в JSON
     * @return Json запись экземпляра класса Corporate
     */
    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        if (id == null){
            System.out.printf("ID of new Corporate Client ", this.getClass(), " is NULL!");
            map.put("id", "");
        } else {
            System.out.printf("ID of new Corporate Client ", this.getClass(), " is ", id.get());
            map.put("id", String.valueOf(id.get()));
        }

        map.put("email", email.get());
        map.put("phone", phone.get());
        map.put("company_name", companyName.get());
        map.put("legal_address", legalAddress.get());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
