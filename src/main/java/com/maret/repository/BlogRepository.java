package com.maret.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maret.model.Blog;

@Repository
public interface BlogRepository extends CrudRepository<Blog,Integer> {
}
