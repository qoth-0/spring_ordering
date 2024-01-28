package com.example.orderSystem.orderItem.repository;

import com.example.orderSystem.member.domain.Member;
import com.example.orderSystem.orderItem.domain.OrderItem;
import com.example.orderSystem.ordering.domain.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrdering(Ordering ordering);
}
