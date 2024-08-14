package com.mysettlement.member.controller;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.entity.MemberRole;
import com.mysettlement.member.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            memberRepository.save(Member.of(("member" + i), ("member" + i + "@test.test"), MemberRole.DEFAULT));
        }
        memberRepository.save(Member.of("방망이깎는노인", "regular.jk.kim@gmail.com", MemberRole.DEFAULT));
    }
}
