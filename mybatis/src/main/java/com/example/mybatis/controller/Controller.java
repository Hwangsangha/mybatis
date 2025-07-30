package com.example.mybatis.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor	//생성자 자동생성 (fianl 붙은 필드를 주입받음)
public class Controller {
	private final UserMapper userMapper;	// MyBatis Mapper 주입
	
	//전체 사용자 조회 (GET /users)
	@GetMapping("/users")
	public List<User> getUser(){
		return userMapper.findAll();
	}
	
	// ID로 사용자 조회
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") int id) {
		return userMapper.findById(id);
	}
	
	//사용자 등록 (POST /users)
	@PostMapping("/users")
	public String addUser(@RequestBody User user) {
		int result = userMapper.insert(user);
		
		if(result > 0) {
			return "사용자 등록 성공";
		}else {
			return "사용자 등록 실패";
		}
	}
	
	//사용자 수정
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int id, @RequestBody User user) {
		user.setId(id);// ID 설정
		int result = userMapper.update(user);
		return result == 1 ? "수정 성공" : "수정 실패";
	}
}
