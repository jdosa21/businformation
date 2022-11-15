package com.project.bus.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.persistence.*;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;

import lombok.Builder;




@EnableJpaAuditing
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)



public class Board {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author; // 작성자

    private String title; // 제목

    private String content; // 내용

    @CreatedDate
    private LocalDateTime date; // 작성시간

//    @Column(columnDefinition = "integer default 0", nullable = false)
//    // private int hit; // 조회수

}
