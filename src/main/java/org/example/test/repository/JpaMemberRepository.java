package org.example.test.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.example.test.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //jpa가 insert 쳐줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
       Member member = em.find(Member.class , id); // 조회 / 조회 할 type, 식별자 넣어줌.
       return Optional.ofNullable(member); //값이 없을 수도 있으니 Optioanl
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
         return em.createQuery("select m from Member m", Member.class) //member 객체 자체를 조회 m
                .getResultList();
    }
}
