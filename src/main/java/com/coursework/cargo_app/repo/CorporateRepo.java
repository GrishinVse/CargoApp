package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Corporate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateRepo extends JpaRepository<Corporate, Long> {
}
