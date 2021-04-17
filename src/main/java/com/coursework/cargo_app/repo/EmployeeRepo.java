package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
}
