package com.example.mybatis.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor	//생성자 자동생성 (fianl 붙은 필드를 주입받음)
public class Controller {
	
	private final UserMapper userMapper;	// MyBatis Mapper 주입
	
	@GetMapping("/users")
	public String getUserList(Model model) {
		List<User> users = userMapper.findAll();
		model.addAttribute("users", users);
		return "userList";
	}
	
//	//전체 사용자 조회 (GET /users)
//	@GetMapping
//	public List<User> getUser(){
//		return userMapper.findAll();
//	}
	
	// ID로 사용자 조회
	@GetMapping("{id}")
	public User getUserById(@PathVariable("id") int id) {
		return userMapper.findById(id);
	}
	
	//사용자 등록 (POST /users)
	@PostMapping("/users")
	public String addUser(@ModelAttribute User user) {
		userMapper.insert(user);
		return "redirect:/users";
	}
	
	//사용자 수정
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int id, @RequestBody User user) {
		user.setId(id);// ID 설정
		int result = userMapper.update(user);
		return result > 0 ? "redirect:/users" : "수정 실패";
	}
	
	//사용자 삭제
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		int result = userMapper.deleteById(id);
		return result > 0 ? "삭제성공" : "삭제실패";
	}
	
	@GetMapping("/users/new")
	public String showUserForm(Model model) {
		model.addAttribute("user", new User());	//빈 user 객체 전달
		return "userForm";	//위 html파일을 렌더링
	}
	
	@GetMapping("/users/update/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		User user = userMapper.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("isUpdate", true); //수정폼 여부 표시
		return "userForm";
	}
}
