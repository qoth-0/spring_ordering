package com.example.orderSystem.orderItem.service;

import com.example.orderSystem.orderItem.domain.OrderItem;
import com.example.orderSystem.orderItem.dto.OrderItemResDto;
import com.example.orderSystem.orderItem.repository.OrderItemRepository;
import com.example.orderSystem.ordering.domain.Ordering;
import com.example.orderSystem.ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderingRepository orderingRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, OrderingRepository orderingRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderingRepository = orderingRepository;
    }

    public List<OrderItemResDto> orderItemDetail(Long id) {
        Ordering ordering = orderingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<OrderItem> orderItems = orderItemRepository.findByOrdering(ordering);
        List<OrderItemResDto> orderItemResDtos = new ArrayList<>();
        for(OrderItem a : orderItems) {
            OrderItemResDto orderItemResDto = new OrderItemResDto();
            orderItemResDto.setOrderId(a.getOrdering().getId());
            orderItemResDto.setQuantity(a.getQuantity());
            orderItemResDto.setName(a.getItem().getName());
            orderItemResDtos.add(orderItemResDto);
        }
        return orderItemResDtos;
    }

}
