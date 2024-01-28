package com.example.orderSystem.item.domain;

import com.example.orderSystem.ordering.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    private int price;
    private int stockQuantity;
    private String imagePath;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // current_timestamp 설정
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // update 시 갱신
    private LocalDateTime updatedTime;

    public void stockUpdate(OrderStatus orderStatus, Long count) {
        if(orderStatus == OrderStatus.ORDERED) {
            this.stockQuantity -= count;
        }
        if(orderStatus == OrderStatus.CANCELED) {
            this.stockQuantity += count;
        }
    }
}
