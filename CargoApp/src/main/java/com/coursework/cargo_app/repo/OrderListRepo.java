package com.coursework.cargo_app.repo;

import com.coursework.cargo_app.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepo extends JpaRepository<OrderList, Long> {
}
