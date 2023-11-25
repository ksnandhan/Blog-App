package com.blog.app.payloads;

import jakarta.validation.constraints.NotBlank;


import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private int categoryId;
	@NotBlank
	@Size(min = 4,message = "min size of category title is 4")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=5,message = "min size of category description is 5")
	private String categoryDescription;
}
