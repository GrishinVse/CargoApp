package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepo extends JpaRepository<Contract, Long> {
}
