package com.blog.app.payloads;

import java.util.List;
import lombok.*;
@NoArgsConstructor
@Getter
@Setter

public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;  //no.of elements
	private int totalPages;
	
	private boolean lastPage;
	
}
