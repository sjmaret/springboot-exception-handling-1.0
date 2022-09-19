package com.maret.service;

import java.util.List;

import com.maret.exception.BlogAlreadyExistsException;
import com.maret.exception.BlogNotFoundException;
import com.maret.model.Blog;

public interface BlogService {

    Blog saveBlog(Blog blog) throws BlogAlreadyExistsException;
    List<Blog> getAllBlogs() throws BlogNotFoundException;
    Blog getBlogById(int id) throws BlogNotFoundException;
}
