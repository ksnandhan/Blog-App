package com.blog.app.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.app.payloads.UserApiResponse;
import com.blog.app.payloads.CategoryApiResponse;
import com.blog.app.payloads.CategoryDto;
import com.blog.app.payloads.UserDto;
import com.blog.app.services.CategoryService;
import com.blog.app.utils.BlogConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryservice; 
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryApiResponse>createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory=this.categoryservice.createCategory(categoryDto);
		CategoryApiResponse response =new CategoryApiResponse();
		response.setCode(BlogConstants.CODE_CREATED);
		response.setStatus(BlogConstants.STATUS_CREATED);
		response.setMessage(BlogConstants.CATEGORY_CREATED_MSG);
		response.setCategory(createCategory);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryApiResponse>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId)
	{
		CategoryDto updatedCategory=this.categoryservice.updateCategory(categoryDto,catId);
		CategoryApiResponse response=new CategoryApiResponse();
		response.setCode(BlogConstants.CODE_OK);
		response.setStatus(BlogConstants.STATUS_OK);
		response.setMessage(BlogConstants.CATEGORY_UPDATED_MSG);
		response.setCategory(updatedCategory);
		//return new ResponseEntity<CategoryApiResponse>(response,HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<CategoryApiResponse>deleteCategory(@PathVariable Integer catId)
	{
		this.categoryservice.deleteCategory(catId);
		
		CategoryApiResponse response =new CategoryApiResponse();
		response.setCode(BlogConstants.CODE_OK);
		response.setStatus(BlogConstants.STATUS_OK);
		response.setMessage(BlogConstants.CATEGORY_DELETED_MSG);
		return ResponseEntity.ok(response);
		
	}
	
	
	//get
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer catId)
	{
		CategoryDto categoryDto = this.categoryservice.getCategory(catId);
		return ResponseEntity.ok(categoryDto);
	}
	
	
	//getall
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>>getCategories()
	{
		List<CategoryDto> categories = this.categoryservice.getCategories();
		return ResponseEntity.ok(categories);
	}
}
