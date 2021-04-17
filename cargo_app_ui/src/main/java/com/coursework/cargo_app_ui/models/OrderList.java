package com.coursework.cargo_app_ui.models;

import javafx.beans.property.*;

public class OrderList {
    private final LongProperty id;
    private final StringProperty startAddress;
    private final StringProperty otherAddress;
    private final StringProperty endAddress;
    private final StringProperty orderType;
    private final StringProperty description;

    // ???
    private final ObjectProperty clientId;
    //private final ObjectProperty corporateId;
    //private final ObjectProperty individualId;

    private final ObjectProperty employeeId;
    private final ObjectProperty transportId;

    public OrderList(){
        this.id =new SimpleLongProperty(Long.valueOf(1));
        this.startAddress = new SimpleStringProperty("Улица Ленина 1");
        this.otherAddress = new SimpleStringProperty("Улица Ленина 2");
        this.endAddress = new SimpleStringProperty("Улица Ленина 3");
        this.orderType = new SimpleStringProperty("working");
        this.description = new SimpleStringProperty("no description");

        this.clientId = null;

        this.employeeId = null;
        this.transportId = null;
    }

    public OrderList(String startAddress, String otherAddress, String endAddress, String orderType, String description,
                     Object clientId, Object employeeId,  Object transportId) {
        this.id = null;
        this.startAddress = new SimpleStringProperty(startAddress);
        this.otherAddress = new SimpleStringProperty(otherAddress);
        this.endAddress = new SimpleStringProperty(endAddress);
        this.orderType = new SimpleStringProperty(orderType);
        this.description = new SimpleStringProperty(description);

        this.clientId = new SimpleObjectProperty<>(clientId);

        this.employeeId = new SimpleObjectProperty<>(employeeId);
        this.transportId = new SimpleObjectProperty<>(transportId);
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

    public Object getClientId() {
        return clientId.get();
    }

    public ObjectProperty clientIdProperty() {
        return clientId;
    }

    public Object getEmployeeId() {
        return employeeId.get();
    }

    public ObjectProperty employeeIdProperty() {
        return employeeId;
    }

    public Object getTransportId() {
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

    public void setClientId(Object clientId) {
        this.clientId.set(clientId);
    }

    public void setEmployeeId(Object employeeId) {
        this.employeeId.set(employeeId);
    }

    public void setTransportId(Object transportId) {
        this.transportId.set(transportId);
    }
}
