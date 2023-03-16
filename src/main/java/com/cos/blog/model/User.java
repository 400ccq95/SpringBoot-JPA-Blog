package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // User클래스가 통해서 MySQL에테이블 이 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더패턴

public class User {
   @Id //primarykey
   @GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
   @Column(nullable =false,length=100,unique = true)
	private String username;
   @Column(nullable=false,length=100)
	private String password;
   @Column(nullable=false,length=50)
	private String email;
	
   @Enumerated(EnumType.STRING)
    private RoleType role; //Enum
   
   private String oauth;
   @CreationTimestamp
	private Timestamp createDate;
}
