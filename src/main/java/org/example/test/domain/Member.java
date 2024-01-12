package org.example.test.domain;

import jakarta.persistence.*;

@Entity //jpa가 관리하는 엔티티
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 생성해줌 IDENTITY를 이용
    private Long id;

    //@Column(name = "username") //db에 있는 컬럼명이 username일 때 매핑해줌
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
