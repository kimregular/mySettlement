package com.mysettlement.member.controller;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String home() {
        return "you are in";
    }

    @GetMapping("/api/member")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
