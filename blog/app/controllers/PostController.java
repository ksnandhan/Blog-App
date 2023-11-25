package com.blog.app.controllers;

import java.io.IOException;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.entities.Post;
import com.blog.app.payloads.PostApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;
import com.blog.app.services.FileService;
import com.blog.app.services.PostService;
import com.blog.app.utils.BlogConstants;

@RestController

@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto>posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	//get by category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>>getPostsByCategory(@PathVariable Integer categoryId)
		{
			List<PostDto>posts=this.postService.getPostsByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		}
		
	//get all posts
		@GetMapping("/posts")
		public ResponseEntity<PostResponse>getAllPosts(
				@RequestParam(value="pageNumber",defaultValue=BlogConstants.PAGE_NUMBER,required =false) Integer pageNumber ,
				@RequestParam(value="pageSize",defaultValue=BlogConstants.PAGE_SIZE,required = false)Integer pageSize,
				@RequestParam(value="sortBy",defaultValue=BlogConstants.SORT_BY,required=false)String sortBy,
				@RequestParam(value="sortDir",defaultValue=BlogConstants.SORT_DIR,required =false)String sortDir)
		{
		
			PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
			
			return ResponseEntity.ok(postResponse);
		}
		
		
	//get post by id
		
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto>getPostById(@PathVariable Integer postId)
		{
			PostDto postDto=this.postService.getPostById(postId);
			return ResponseEntity.ok(postDto);
		}
		
	//delete post
		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<PostApiResponse> deletePost(@PathVariable Integer postId)
		{
			this.postService.deletePost(postId);
			PostApiResponse response = new PostApiResponse();
			response.setCode(BlogConstants.CODE_OK);
			response.setStatus(BlogConstants.STATUS_OK);
			response.setMessage(BlogConstants.POST_DELETED_MSG);
			return ResponseEntity.ok(response);
		}
		
	//update post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostApiResponse>updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId)
		{
			PostDto updatedPost=this.postService.updatePost(postDto, postId);
			PostApiResponse response = new PostApiResponse();
			response.setCode(BlogConstants.CODE_OK);
			response.setStatus(BlogConstants.STATUS_OK);
			response.setMessage(BlogConstants.POST_UPDATED_MSG);
			response.setPost(updatedPost);
			return ResponseEntity.ok(response);
		}
		
	//search
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keywords") String keyWords)
		{
			List<PostDto>result=this.postService.searchPosts(keyWords);
			return  ResponseEntity.ok(result);
		}
		
		//post image upload
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto>uploadPostImage(@RequestParam("image")MultipartFile image,@PathVariable Integer postId) throws IOException
		{
			PostDto postDto=this.postService.getPostById(postId);
			String fileName=this.fileService.uploadImage(path, image);
			
			postDto.setImageName(fileName);
			PostDto updatePost=this.postService.updatePost(postDto, postId);
			return ResponseEntity.ok(updatePost);
		}
}
