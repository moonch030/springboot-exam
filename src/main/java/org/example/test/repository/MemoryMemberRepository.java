package org.example.test.repository;

import org.example.test.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private  static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 세팅
        store.put(member.getId(),member); //store에 저장 / 그럼 map에 저장이 됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 이여도 optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return  store.values().stream()
                .filter(member -> member.getName().equals(name)) //getName이 parameter로 넘어온 name이랑 같은지 확인 / 같은 경우에만 필터링 찾으면 반환
                .findAny(); //하나라도 찾으면 optional로 반환 / 없으면 optional에 null이 들어가서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store.values가 9번째줄에 있는 member들을 반환
    }

    public void clearStore(){
        store.clear();
    }
}
