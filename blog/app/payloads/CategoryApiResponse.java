package com.blog.app.payloads;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CategoryApiResponse {

	private String code;
	private String status;
	private String message;
	private CategoryDto category;
	public CategoryApiResponse(String code, String status, String message, CategoryDto category) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
		this.category = category;
	}
	public CategoryApiResponse(String code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
	
}
