package com.smaret.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maret.exception.BlogAlreadyExistsException;
import com.maret.exception.BlogNotFoundException;
import com.maret.model.Blog;
import com.maret.service.BlogService;

@RestController
@RequestMapping("api/v1")
public class BlogController {
	private BlogService blogService;

	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping("/blog")
	public ResponseEntity<Blog> saveBlog(@RequestBody Blog blog) throws BlogAlreadyExistsException {
		Blog savedBlog = blogService.saveBlog(blog);
		return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);

	}

	@GetMapping("/blogs")
	public ResponseEntity<List<Blog>> getAllBlogs() throws BlogNotFoundException {
		return new ResponseEntity<List<Blog>>((List<Blog>) blogService.getAllBlogs(), HttpStatus.OK);
	}

	// Approach 1: try-catch
	@GetMapping("blog/{id}")
	public ResponseEntity<Blog> getBlogById(@PathVariable("id") int id) {

		try {
			return new ResponseEntity<Blog>(blogService.getBlogById(id), HttpStatus.OK);
		} catch (BlogNotFoundException blogNotFoundException) {
			return new ResponseEntity(blogNotFoundException.getMessage(), HttpStatus.CONFLICT);
		}
	}

	// Approach 2: ExceptionHandler
	@ExceptionHandler(value = BlogAlreadyExistsException.class)
	public ResponseEntity<String> BlogAlreadyExistsException(BlogAlreadyExistsException blogAlreadyExistsException) {
		return new ResponseEntity<String>("Blog already exists", HttpStatus.CONFLICT);
	}
}
