package com.project.bus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // DB에 있는 테이블을 의미
@Data // Lombok을 이용해 클래스의 데이터를 넘겨줌
public class Reserve {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 기본 키

    private String ri; // 승차 예약 버스

    private String ro; // 하차 예약 장소
}
