package com.example.orderSystem.ordering.dto;

import com.example.orderSystem.member.domain.Member;
import com.example.orderSystem.ordering.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class OrderingListResDto {
    private Long id;
    private Member member;
    private OrderStatus orderStatus;
}
