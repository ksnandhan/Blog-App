package com.blog.app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostApiResponse {

	private String code;
	private String status;
	private String message;
	private CategoryDto category;
	private UserDto user;
	private PostDto post;
	public PostApiResponse(String code, String status, String message,PostDto postDto) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.post=post;
	}
	public PostApiResponse(String code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	
	
}
