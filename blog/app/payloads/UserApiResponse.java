package com.blog.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserApiResponse {

//	private String message;
//	private boolean success;
	
	
	private String code;
	private String status;
	private String message;
	private UserDto user;
	public UserApiResponse(String code, String status, String message, UserDto user) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.user = user;
	}
	public UserApiResponse(String code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	
}
