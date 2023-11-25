package com.blog.app.services;

import org.springframework.stereotype.Service;

import com.blog.app.payloads.CommentDto;

@Service
public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	
	
	
	
}
