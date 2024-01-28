package com.example.orderSystem.member.repository;

import com.example.orderSystem.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
