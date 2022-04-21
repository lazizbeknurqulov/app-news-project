package com.example.appnewsproject.repository;

import com.example.appnewsproject.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     Comment findByCreatedByAndId(Long createdBy, Long id);
     List<Comment> findByPost_Id(Long post_id);

}
