package com.blog.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.User;
//this repository provides functionalities,database operations
public interface UserRepo extends JpaRepository<User, Integer>{

}
