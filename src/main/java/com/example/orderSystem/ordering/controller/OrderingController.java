package com.example.orderSystem.ordering.controller;

import com.example.orderSystem.ordering.domain.Ordering;
import com.example.orderSystem.ordering.dto.OrderingListResDto;
import com.example.orderSystem.ordering.dto.OrderingNewReqDto;
import com.example.orderSystem.ordering.service.OrderingService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderingController {

    private final OrderingService orderingService;

    @Autowired
    public OrderingController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @GetMapping("/orders")
    public List<OrderingListResDto> orderingList() {
        return orderingService.findAll();
    }

    @PostMapping("/order/new")
    public String orderNew(OrderingNewReqDto orderingNewReqDto) {
        orderingService.orderNew(orderingNewReqDto);
        return "ok";
    }

    @PatchMapping("/order/{id}/cancle")
    public String orderCancle(@PathVariable Long id) {
        orderingService.orderCancle(id);
        return "ok";
    }
}
