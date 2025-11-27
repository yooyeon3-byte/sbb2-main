package com.mysite.sbb.member.service;

import com.mysite.sbb.member.dto.MemberDto;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(@Valid MemberDto memberDto) {
        Member member = Member.builder()
                .username(memberDto.getUsername())
//        .password(memberDto.getPassword1())
                .password(passwordEncoder.encode(memberDto.getPassword1())) // 비밀번호 암호화
                .email(memberDto.getEmail())
                .gender(memberDto.getGender())
                .department(memberDto.getDepartment())
                .active(memberDto.getActive())
                .build();

        memberRepository.save(member);
    }
}