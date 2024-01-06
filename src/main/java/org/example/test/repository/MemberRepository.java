package org.example.test.repository;

import org.example.test.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원이 반환됨
    Optional<Member> findById(Long id); //id로 회원을 찾음 /null이 들어왔을때 Optional로 감싸서 반환, 자바8에 들어있는 기능
    Optional<Member> findByName(String name); //name으로 회원을 찾음
    List<Member> findAll();

}
