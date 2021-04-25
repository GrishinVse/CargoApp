package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepo extends JpaRepository<Transport, Long> {
}
