package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Employee;
import com.coursework.cargo_app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){ this.employeeService = employeeService; }

    @PostMapping(value = "/cargo_app/employees")
    public ResponseEntity<?> create(@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/employees")
    public ResponseEntity<List<Employee>> findAll(){
        final List<Employee> employeeList = employeeService.findAll();

        return employeeList != null && !employeeList.isEmpty()
                ? new ResponseEntity<>(employeeList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/employees/{id}")
    public ResponseEntity<Optional<Employee>> find(@PathVariable(name="id") Long id){
        final Optional<Employee> employee = employeeService.find(id);

        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee updateEmployee) {
        return employeeService.find(id).map(employee -> {
            employee.setFirst_name(updateEmployee.getFirst_name());
            employee.setLast_name(updateEmployee.getLast_name());
            employee.setHire_date(updateEmployee.getHire_date());
            employee.setRating(updateEmployee.getRating());

            employee.setJob_id(updateEmployee.getJob_id());

            employeeService.update(employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(name = "id") Long id) {
        return employeeService.find(id).map(employee -> {
            employeeService.delete(employee);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
