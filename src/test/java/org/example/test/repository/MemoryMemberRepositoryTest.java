package org.example.test.repository;

import org.example.test.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); // MemoryMemberRepository 클래스의 새로운 인스턴스를 생성

    @AfterEach // JUnit에서 각 테스트 메소드가 실행된 후에 실행되어야 하는 메소드를 나타내는 어노테이션. clearStore() 메소드를 호출하여 테스트 이후에 저장소를 초기화
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); //Member 클래스의 새로운 인스턴스 생성
        member.setName("spring"); //객체 이름을 spring으로 설정

        repository.save(member); //repository에 회원 저장, "spring"이 저장 된다.

        Member result = repository.findById(member.getId()).get(); // 저장된 회원을 조회하여 result 변수에 할당
        assertThat(member).isEqualTo(result); //assertThat을 사용하여 member와 result가 동일한지 확인
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

       Member result =  repository.findByName("spring").get();
       // "spring1"이라는 이름을 갖는 회원을 찾는 것이 아니라 "spring1"이라는 이름과 일치하는 회원을 찾는데,
       // 여기서 "spring1"에 해당하는 회원이 없으므로 Optional에서 get()을 호출하면 NoSuchElementException이 발생

       assertThat(result).isEqualTo(member1); //assertThat을 사용하여 member1과 result가 동일한지 확인
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        //member2를 생성하고 저장한 후, repository.findAll()을 호출하여 모든 회원을 조회하고, 조회된 회원 리스트의 크기가 2인지 확인

    }
}
