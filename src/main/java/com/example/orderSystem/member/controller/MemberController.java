package com.example.orderSystem.member.controller;

import com.example.orderSystem.member.dto.MemberDetailResDto;
import com.example.orderSystem.member.dto.MemberListResDto;
import com.example.orderSystem.member.dto.MemberSaveReqDto;
import com.example.orderSystem.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //    회원 생성
    @PostMapping("/member/new")
    public String memberCreate(MemberSaveReqDto memberSaveReqDto) {
        memberService.memberCreate(memberSaveReqDto);
        return "ok";
    }

    //    회원 목록 조회
    @GetMapping("/members")
    public List<MemberListResDto> memberList() {
        return memberService.memberList();
    }

    //    회원 상세 조회
//    @GetMapping("/member/{id}/orders")
//    public MemberDetailResDto memberDetail(@PathVariable Long id) {
//        return memberService.memberDetail(id);
//    }
}
