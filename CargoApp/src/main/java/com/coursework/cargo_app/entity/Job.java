package com.coursework.cargo_app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Job implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "min_salary")
    private Float min_salary;

    public Float getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(Float min_salary) {
        this.min_salary = min_salary;
    }

    // Foreign keys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "job_id")
    private Set<Employee> employees;

    public Job(){}

}
