package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.mybatis.domain.User;

@Mapper
public interface UserMapper {
	List<User> findAll();				//전체 사용자 목록 조회
	User findById(@Param("id") int id);	//id로 사용자 목록 조회
	int insert(User user);				//사용자 등록
	int update(User user);				// 사용자 수정
}
