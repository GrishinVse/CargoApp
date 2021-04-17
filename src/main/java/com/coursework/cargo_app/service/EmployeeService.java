package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.Employee;
import com.coursework.cargo_app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public void create(Employee employee){
        employeeRepo.save(employee);
    }

    public void update(Employee employee) { employeeRepo.save(employee); }

    public void delete(Employee employee) { employeeRepo.delete(employee); }

    public List<Employee> findAll(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> find(Long id){
        return employeeRepo.findById(id);
    }
}
