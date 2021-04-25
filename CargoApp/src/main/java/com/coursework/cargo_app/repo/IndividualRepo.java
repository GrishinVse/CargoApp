package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepo extends JpaRepository<Individual, Long> {
}
