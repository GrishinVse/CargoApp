package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.OrderList;
import com.coursework.cargo_app.service.OrderListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderListController {
    private final OrderListService orderListService;

    @Autowired
    public OrderListController(OrderListService orderListService){ this.orderListService = orderListService; }

    @PostMapping(value = "/cargo_app/order_lists")
    public ResponseEntity<?> create(@RequestBody OrderList orderList){
        orderListService.create(orderList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/cargo_app/order_lists")
    public ResponseEntity<List<OrderList>> findAll(){
        final List<OrderList> orderListList = orderListService.findAll();

        return orderListList != null && !orderListList.isEmpty()
                ? new ResponseEntity<>(orderListList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cargo_app/order_lists/{id}")
    public ResponseEntity<Optional<OrderList>> find(@PathVariable(name="id") Long id){
        final Optional<OrderList> orderList = orderListService.find(id);

        return orderList != null
                ? new ResponseEntity<>(orderList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cargo_app/order_lists/{id}")
    public ResponseEntity<?> updateOrderList(@PathVariable(name = "id") Long id, @RequestBody OrderList updateOrderList) {
        return orderListService.find(id).map(orderList -> {
            orderList.setDescription(updateOrderList.getDescription());
            orderList.setOrder_type(updateOrderList.getOrder_type());

            orderList.setStart_address(updateOrderList.getStart_address());
            orderList.setOther_address(updateOrderList.getOther_address());
            orderList.setEnd_address(updateOrderList.getEnd_address());

            orderList.setIndividual(updateOrderList.getIndividual());
            orderList.setCorporate(updateOrderList.getCorporate());
            orderList.setTransport(updateOrderList.getTransport());
            orderList.setEmployee(updateOrderList.getEmployee());

            orderListService.update(orderList);
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/cargo_app/order_lists/{id}")
    public ResponseEntity<?> deleteOrderList(@PathVariable(name = "id") Long id) {
        return orderListService.find(id).map(orderList -> {
            orderListService.delete(orderList);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
