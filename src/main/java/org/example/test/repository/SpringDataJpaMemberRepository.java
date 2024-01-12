package org.example.test.repository;

import org.example.test.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //스프링 데이터가 제공하는 JpaRepository

    @Override
    Optional<Member>  findByName(String name);
}
