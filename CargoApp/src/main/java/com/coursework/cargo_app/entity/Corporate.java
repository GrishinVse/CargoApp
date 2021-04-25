package com.coursework.cargo_app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Corporate extends Client implements Serializable {

    @Column(name = "company_name")
    private String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Column(name = "legal_address")
    private String legal_address;

    public String getLegal_address() {
        return legal_address;
    }

    public void setLegal_address(String legal_address) {
        this.legal_address = legal_address;
    }

    public Corporate(String email, String phone, String company_name, String legal_address) {
        super(email, phone);
        this.company_name = company_name;
        this.legal_address = legal_address;
    }

    // Foreign keys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "corporate")
    private Set<Contract> contracts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "corporate")
    private Set<OrderList> orders;
}
