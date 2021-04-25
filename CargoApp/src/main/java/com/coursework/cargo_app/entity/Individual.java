package com.coursework.cargo_app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Individual extends Client implements Serializable {

    @Column(name = "first_name")
    private String first_name;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    private String last_name;

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Individual(String email, String phone, String first_name, String last_name) {
        super(email, phone);
        this.first_name = first_name;
        this.last_name = last_name;
    }

    // Foreign keys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "individual")
    private Set<OrderList> orders;

}