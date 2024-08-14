package com.mysettlement.member.service;

import com.mysettlement.member.entity.Member;
import com.mysettlement.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberWithId(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다"));
    }

    public Member findMemberWithEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));
    }
}
