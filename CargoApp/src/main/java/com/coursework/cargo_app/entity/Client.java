package com.coursework.cargo_app.entity;

import javax.persistence.*;
import java.util.Set;

@MappedSuperclass
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client(){}

    public Client(String email, String phone){
        this.email = email;
        this.phone = phone;
    }

    /*
    // Foreign keys
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Order> orders;

     */

}
