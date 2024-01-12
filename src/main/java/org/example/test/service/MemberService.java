package org.example.test.service;

import org.example.test.domain.Member;
import org.example.test.repository.MemberRepository;
import org.example.test.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
    *회원 가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증 메서드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //반환된 값이 Optional이 비어있지 않으면 즉, 이미 회원이 존재 한다면
            .ifPresent(m->{ //여기가 실행
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll(); // 모든 회원을 조회하여 리스트로 반환
    }

    public Optional<Member> findOne(Long memberId){ //매개변수로 전달된 회원 ID에 해당하는 특정 회원을 조회
        return memberRepository.findById(memberId); // 찾으면 Optional<Member>형태로 반환
    }
}
