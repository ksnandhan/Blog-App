package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDto;

public interface UserService {
	//These are the operations that upon an entity that can be performed
 UserDto createUser(UserDto user);
 
 UserDto updateUser(UserDto user,Integer userId);
 
 UserDto getUserById(Integer userId);
 
 void deleteUser(Integer userId);
 
 List<UserDto>getAllUsers();
}
