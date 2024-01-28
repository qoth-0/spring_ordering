package com.example.orderSystem.ordering.dto;

import com.example.orderSystem.item.domain.Item;
import com.example.orderSystem.member.domain.Member;
import lombok.Data;

import java.util.List;

@Data
public class OrderingNewReqDto {
    private Member member;
    private List<Long> itemId;
    private List<Long> count;
}
