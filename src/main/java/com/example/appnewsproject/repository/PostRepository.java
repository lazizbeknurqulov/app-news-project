package com.example.appnewsproject.repository;

import com.example.appnewsproject.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
