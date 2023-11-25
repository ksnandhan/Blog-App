package com.blog.app.payloads;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
public class CommentApiResponse {

	private String code;
	private String status;
	private String message;
	public CommentApiResponse(String code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	
	
}
