package com.project.bus.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserInfoDto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 기본키
  private String email; // 사용자의 아이디
  private String password; // 비밀번호
  private String auth; // 접근권한
}
