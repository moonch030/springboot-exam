package org.example.test.repository;

import org.example.test.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{ // MemberRepository 인터페이스를 구현한 MemoryMemberRepository 클래스, 회원 정보를 메모리에 저장하는 단순한 저장소를 구현

    private  static Map<Long, Member> store = new HashMap<>(); //회원 정보를 저장하는 Map, 회원 정보를 저장하는데 사용되는 HashMap. 키는 회원 ID(Long), 값은 Member 객체.
    private  static Long sequence = 0L; // 회원의 ID를 생성하기 위한 변수로, save() 메소드에서 회원을 저장할 때 마다 1씩 증가시켜 고유한 ID를 부여

    @Override
    public Member save(Member member) { // 회원을 저장하는 메소드. sequence를 증가시켜 고유한 ID를 생성하고, 회원 객체의 ID를 설정한 후 store에 저장
        member.setId(++sequence); //id 세팅
        store.put(member.getId(),member); //store에 저장 / 그럼 map에 저장이 됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // 주어진 ID로 회원을 찾아 반환하며, Optional로 감싸서 반환. 만약 해당 ID로 회원을 찾지 못하면 null 대신 Optional.empty()를 반환
        return Optional.ofNullable(store.get(id)); //ID를 사용하여 회원을 찾아 null 이여도 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) { //주어진 이름으로 회원을 찾아 반환하며, 이름이 일치하는 모든 회원 중 임의의 한 명을 찾아 Optional로 감싸서 반환
        return  store.values().stream()
                .filter(member -> member.getName().equals(name)) //getName이 parameter로 넘어온 name이랑 같은지 확인 / 같은 경우에만 필터링 찾으면 반환
                .findAny(); //하나라도 찾으면 optional로 반환 / 없으면 optional에 null이 들어가서 반환
    }

    @Override
    public List<Member> findAll() { // 저장된 모든 회원의 리스트를 반환
        return new ArrayList<>(store.values()); //store.values가 9번째줄에 있는 member들을 반환
    }

    public void clearStore(){ // 저장소의 모든 회원 정보를 제거하여 초기화. 이 메소드는 테스트 등에서 사용할 때 유용
        store.clear(); // 저장소 초기화
    }
}
