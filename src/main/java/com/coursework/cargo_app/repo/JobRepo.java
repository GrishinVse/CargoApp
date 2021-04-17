package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepo extends JpaRepository<Job, Long> {
}
