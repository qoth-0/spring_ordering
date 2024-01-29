package com.example.orderSystem.orderItem.controller;

import com.example.orderSystem.orderItem.dto.OrderItemResDto;
import com.example.orderSystem.orderItem.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderitems/{id}")
    public List<OrderItemResDto> orderItemDetail(@PathVariable Long id) {
        return orderItemService.orderItemDetail(id);
    }
}
