package com.mysettlement.member.repository;

import com.mysettlement.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    Optional<Member> findByEmail(String email);
}
