package org.example.test.service;

import org.assertj.core.api.Assertions;
import org.example.test.domain.Member;
import org.example.test.repository.MemberRepository;
import org.example.test.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 메서드가 실행되기 전에 실행되어야 하는 메서드
    public void beforeEach(){ // 테스트를 수행하기 전에 MemoryMemberRepository와 MemberService를 초기화
        memberRepository = new MemoryMemberRepository(); // MemoryMemberRepository 인스턴스와 MemberService 인스턴스를 생성
        memberService = new MemberService(memberRepository);
    }
    @AfterEach // JUnit에서 각 테스트 메소드가 실행된 후에 실행되어야 하는 메소드를 나타내는 어노테이션. clearStore() 메소드를 호출하여 테스트 이후에 저장소를 초기화
    public void afterEach(){ //  각 테스트를 수행한 후에는 저장소가 초기화되어 다음 테스트에 영향을 주지 않게
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 새로운 멤버 객체를 생성하고 이름을 설정
        Member member = new Member();
        member.setName("hello");

        //when memberService.join(member)를 호출하여 회원을 가입
        Long saveId = memberService.join(member);

        //then findOne 메서드를 사용하여 저장된 회원을 조회하고, 회원 이름이 예상과 일치하는지 확인
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given  두 개의 멤버 객체를 생성하고 이름을 설정
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when 첫 번째 멤버를 가입시키고, 두 번째 멤버를 가입시키려고 할 때 예외를 발생시키는지 확인
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}