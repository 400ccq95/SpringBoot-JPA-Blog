package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id //Primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id;  // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content; //
	
	@ManyToOne //하나의 게시글에 여러개 답변을 쓸수있다
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 하나의 유저가 여러개의 답변을 쓸수있다.
	@JoinColumn(name="userId")
	private User user;
	 
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
