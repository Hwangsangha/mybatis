package com.example.mybatis.domain;

import lombok.Data;

@Data
public class User {
	private int id;			//user테이블 id컬럼
	private String name;	//name 컬럼
	private String email;	//email 컬럼
}
