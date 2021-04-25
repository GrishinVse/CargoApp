package com.coursework.cargo_app.service;

import com.coursework.cargo_app.entity.OrderList;
import com.coursework.cargo_app.repo.OrderListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderListService {
    @Autowired
    private OrderListRepo orderListRepo;

    public void create(OrderList orderList){
        orderListRepo.save(orderList);
    }

    public void update(OrderList orderList) { orderListRepo.save(orderList); }

    public void delete(OrderList orderList) { orderListRepo.delete(orderList); }

    public List<OrderList> findAll(){
        return orderListRepo.findAll();
    }

    public Optional<OrderList> find(Long id){
        return orderListRepo.findById(id);
    }
}
