package com.example.orderSystem.member.service;

import com.example.orderSystem.member.domain.Member;
import com.example.orderSystem.member.domain.Role;
import com.example.orderSystem.member.dto.MemberDetailResDto;
import com.example.orderSystem.member.dto.MemberListResDto;
import com.example.orderSystem.member.dto.MemberSaveReqDto;
import com.example.orderSystem.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
//    private final PostRepository postRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //, PostRepository postRepository
        this.memberRepository = memberRepository;
//        this.postRepository = postRepository;
    }

    public void memberCreate(MemberSaveReqDto memberSaveReqDto) throws DataIntegrityViolationException {
        Role role = null;
        if(memberSaveReqDto.getRole() == null || memberSaveReqDto.getRole().equals("user")) {
            role = Role.USER;
        }else {
            role = Role.ADMIN;
        }
        Member member = Member.builder()
                .name(memberSaveReqDto.getName())
                .name(memberSaveReqDto.getName())
                .password(memberSaveReqDto.getPassword())
                .build();

        memberRepository.save(member); // save - 내장 메서드
    }
    public List<MemberListResDto> memberList() {
        List<Member> members = memberRepository.findAll(); // findAll - 내장
        List<MemberListResDto> memberListResDtos = new ArrayList<>();
        for(Member member : members) {
            MemberListResDto memberListResDto = new MemberListResDto();
            memberListResDto.setId(member.getId());
            memberListResDto.setName(member.getName());
            memberListResDtos.add(memberListResDto);
        }
        return memberListResDtos;
    }

    //    findById 공통화
    public Member findById(Long id) throws EntityNotFoundException {
        Member member = (Member) memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return member;
    }

//    public MemberDetailResDto memberDetail(Long id) throws EntityNotFoundException{
//        Member member = this.findById(id);
//        String role = null;
//        if(member.getRole() == null || member.getRole().equals(Role.USER)) {
//            role = "일반사용자";
//        }else {
//            role = "관리자";
//        }
//        MemberDetailResDto memberDetailResDto = new MemberDetailResDto(
//                member.getId(),
//                member.getName(),
//                member.getEmail(),
//                member.getPassword(),
//                member.getCreatedTime(),
//                role,
//                member.getPosts().size()
//        );
//        return memberDetailResDto;
//    }

}
