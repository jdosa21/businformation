package com.project.bus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // DB에 있는 테이블을 의미
// JPA가 Bus 테이블에 관한 설정을 읽어 처리해줌
@Data // Lombok을 이용해 클래스의 데이터를 넘겨줌

public class Bus { // 엔티티 작성
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 기본 키

    private String busnum; // 버스 번호

    private String route;// 버스 노선

    private String at; // 도착 예정 시간

}

