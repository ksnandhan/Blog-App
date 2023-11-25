package com.blog.app.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.app.entities.Category;
import com.blog.app.entities.Comment;
import com.blog.app.entities.User;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer PostId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto>comments=new HashSet<>();
	
}
