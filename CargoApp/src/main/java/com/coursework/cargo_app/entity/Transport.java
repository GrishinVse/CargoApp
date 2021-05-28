package com.coursework.cargo_app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Transport implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "licence_plate")
    private String licence_plate;

    public String getLicence_plate() {
        return licence_plate;
    }

    public void setLicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
    }

    @Column(name = "brand")
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "capacity")
    private float capacity;

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    @Column(name = "carrying")
    private float carrying;

    public float getCarrying() {
        return carrying;
    }

    public void setCarrying(float carrying) {
        this.carrying = carrying;
    }

    // Foreign keys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transport")
    private Set<OrderList> orders;

    public Transport(){}

    //public Transport(String brand, float capacity, float carrying, String licence_plate){}
}
