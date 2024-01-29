package com.example.orderSystem.orderItem.dto;

import com.example.orderSystem.item.domain.Item;
import com.example.orderSystem.ordering.domain.Ordering;
import lombok.Data;

@Data
public class OrderItemResDto {
    private Long quantity;
    private String name;
    private Long orderId;
}
