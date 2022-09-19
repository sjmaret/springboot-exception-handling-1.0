package com.maret.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maret.exception.BlogAlreadyExistsException;
import com.maret.exception.BlogNotFoundException;
import com.maret.model.Blog;
import com.maret.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        if (blogRepository.existsById(blog.getBlogId())) {
            throw new BlogAlreadyExistsException();
        }
        Blog savedBlog = blogRepository.save(blog);
        return savedBlog;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public Blog getBlogById(int id) throws BlogNotFoundException {
        Blog blog;
        if (blogRepository.findById(id).isEmpty()) {
            throw new BlogNotFoundException();
        } else {
            blog = blogRepository.findById(id).get();
        }
        return blog;
    }
}
