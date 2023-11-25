package com.blog.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor()
@Getter
@Setter
@Entity() //if we write this by default table is created in database
@Table(name = "users") //to change the name to users
public class User {
	@Id //to distinguish between each other members ,i.e primary in this case
	@GeneratedValue(strategy = GenerationType.AUTO) //it auto increments the values
	private int id;
	//instead of id if we want to change the coloumn name we will use column annotation
	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	private String Email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post> posts= new ArrayList<>();
}
