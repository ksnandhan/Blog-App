package com.blog.app.controllers;
import org.springframework.http.HttpStatus;



import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.UserApiResponse;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.UserService;
import com.blog.app.utils.BlogConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createuser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	//PUT-update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{
		UserDto updatedUser=this.userservice.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	//DELETE-delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<UserApiResponse>deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userservice.deleteUser(uid);
		UserApiResponse response =new UserApiResponse();
		response.setCode(BlogConstants.CODE_OK);
		response.setStatus(BlogConstants.STATUS_OK);
		response.setMessage(BlogConstants.USER_DELETED_MSG);
		return ResponseEntity.ok(response);
	}
	//GET-user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUsers()
	{
		List<UserDto> users = this.userservice.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId)
	{
		UserDto userdto=this.userservice.getUserById(userId);
		return ResponseEntity.ok(userdto);
	}
	
}
