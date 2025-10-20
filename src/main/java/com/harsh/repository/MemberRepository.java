package com.harsh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
