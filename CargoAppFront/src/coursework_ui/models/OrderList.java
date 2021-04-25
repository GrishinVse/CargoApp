package coursework_ui.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.beans.property.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class OrderList implements JSONSerialize{
    private final LongProperty id;
    private final StringProperty startAddress;
    private final StringProperty otherAddress;
    private final StringProperty endAddress;
    private final StringProperty orderType;
    private final StringProperty description;

    // ???
    //private final ObjectProperty clientId;
    private final ObjectProperty corporateId;
    private final ObjectProperty individualId;

    private final ObjectProperty employeeId;
    private final ObjectProperty<Transport> transportId;

    public OrderList(){
        this(null, null, null, null, null);
    }

    public OrderList(String startAddress, String otherAddress, String endAddress, String orderType, String description){
        this.id = null;
        this.startAddress = new SimpleStringProperty(startAddress);
        this.otherAddress = new SimpleStringProperty(otherAddress);
        this.endAddress = new SimpleStringProperty(endAddress);
        this.orderType = new SimpleStringProperty(orderType);
        this.description = new SimpleStringProperty(description);

        this.corporateId = null;
        this.individualId = null;

        this.employeeId = null;
        this.transportId = null;
    }

    public OrderList(Long id, String startAddress, String otherAddress, String endAddress, String orderType, String description){
        this.id = new SimpleLongProperty(id);
        this.startAddress = new SimpleStringProperty(startAddress);
        this.otherAddress = new SimpleStringProperty(otherAddress);
        this.endAddress = new SimpleStringProperty(endAddress);
        this.orderType = new SimpleStringProperty(orderType);
        this.description = new SimpleStringProperty(description);

        this.corporateId = null;
        this.individualId = null;

        this.employeeId = null;
        this.transportId = null;
    }

    /*
    public OrderList(){
        this.id = new SimpleLongProperty(Long.valueOf(1));
        this.startAddress = new SimpleStringProperty("Улица Ленина 1");
        this.otherAddress = new SimpleStringProperty("Улица Ленина 2");
        this.endAddress = new SimpleStringProperty("Улица Ленина 3");
        this.orderType = new SimpleStringProperty("working");
        this.description = new SimpleStringProperty("no description");

        this.corporateId = null;
        this.individualId = null;

        this.employeeId = null;
        this.transportId = null;
    }

     */

    public OrderList(String startAddress, String otherAddress, String endAddress, String orderType, String description,
                     Object corporateId, Object individualId, Object employeeId,  Transport transportId) {
        this.id = null;
        this.startAddress = new SimpleStringProperty(startAddress);
        this.otherAddress = new SimpleStringProperty(otherAddress);
        this.endAddress = new SimpleStringProperty(endAddress);
        this.orderType = new SimpleStringProperty(orderType);
        this.description = new SimpleStringProperty(description);

        this.corporateId = new SimpleObjectProperty<>(corporateId);
        this.individualId = new SimpleObjectProperty<>(individualId);

        this.employeeId = new SimpleObjectProperty<>(employeeId);
        this.transportId = new SimpleObjectProperty<Transport>(transportId);
    }

    // Getters

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public String getStartAddress() {
        return startAddress.get();
    }

    public StringProperty startAddressProperty() {
        return startAddress;
    }

    public String getOtherAddress() {
        return otherAddress.get();
    }

    public StringProperty otherAddressProperty() {
        return otherAddress;
    }

    public String getEndAddress() {
        return endAddress.get();
    }

    public StringProperty endAddressProperty() {
        return endAddress;
    }

    public String getOrderType() {
        return orderType.get();
    }

    public StringProperty orderTypeProperty() {
        return orderType;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    ///

    public Object getCorporateId() {
        return corporateId.get();
    }

    public ObjectProperty corporateIdProperty() {
        return corporateId;
    }

    public Object getIndividualId() {
        return individualId.get();
    }

    public ObjectProperty individualIdProperty() {
        return individualId;
    }

    public Object getEmployeeId() {
        return employeeId.get();
    }

    public ObjectProperty employeeIdProperty() {
        return employeeId;
    }

    public Transport getTransportId() {
        return transportId.get();
    }

    public ObjectProperty transportIdProperty() {
        return transportId;
    }

    // Setters

    public void setStartAddress(String startAddress) {
        this.startAddress.set(startAddress);
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress.set(otherAddress);
    }

    public void setEndAddress(String endAddress) {
        this.endAddress.set(endAddress);
    }

    public void setOrderType(String orderType) {
        this.orderType.set(orderType);
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setCorporateId(Object corporateId) {
        this.corporateId.set(corporateId);
    }

    public void setIndividualId(Object individualId) {
        this.individualId.set(individualId);
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId.set(employeeId);
    }

    public void setTransportId(Transport transportId) {
        this.transportId.set(transportId);
    }

    @Override
    public String toJson() {
        Map<String, String> map = new HashMap<>();
        if (id == null){
            map.put("id", null);
        } else {
            map.put("id", String.valueOf(id.get()));
        }
        map.put("description", description.get());
        map.put("start_address", startAddress.get());
        map.put("other_address", otherAddress.get());
        map.put("end_address", endAddress.get());
        map.put("order_type", orderType.get());

        //map.put("transport_id", transportId.get().toString());

        map.put("transport_id", new Gson().fromJson(transportId.get().toJson(), (Type) JsonObject.class));

        map.put("employee_id", employeeId.get().toString());
        map.put("corporate_id", corporateId.get().toString());
        map.put("individual_id", individualId.get().toString());

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
