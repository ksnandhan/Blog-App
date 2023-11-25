package com.blog.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.CommentApiResponse;
import com.blog.app.payloads.CommentDto;
import com.blog.app.services.CommentService;
import com.blog.app.utils.BlogConstants;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
	{
		CommentDto createdComment=this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<>(createdComment,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<CommentApiResponse>deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		CommentApiResponse commentResponse=new CommentApiResponse();
		commentResponse.setCode(BlogConstants.CODE_OK);
		commentResponse.setStatus(BlogConstants.STATUS_OK);
		commentResponse.setMessage(BlogConstants.COMMENT_DELETED_MSG);
		return ResponseEntity.ok(commentResponse);
	}
	
}
