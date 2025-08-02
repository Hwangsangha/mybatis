package com.example.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor	//생성자 자동생성 (fianl 붙은 필드를 주입받음)
public class UserController {
	
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
	public String createUser(@ModelAttribute User user) {
		userMapper.insert(user);
		return "redirect:/users";
	}
	
	//사용자 수정
	@PutMapping("/users/{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute User user) {
		user.setId(id);// ID 설정
		userMapper.update(user);
		return "redirect:/users";
	}
	
	//사용자 삭제
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userMapper.deleteById(id);
		return "redirect:/users";
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
		return "userUpdate";
	}
}
