package com.example.orderSystem.member.domain;

import com.example.orderSystem.ordering.domain.Ordering;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
//    @Column(nullable = false, length = 20, unique = true)
    private String email;
//    @Column(nullable = false)
    private String password;
//    @Column(nullable = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // current_timestamp 설정
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // update 시 갱신
    private LocalDateTime updatedTime;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ordering> orderings;

}
