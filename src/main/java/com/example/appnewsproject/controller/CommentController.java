package com.example.appnewsproject.controller;

import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.CommentDto;
import com.example.appnewsproject.payload.CommentResponseDto;
import com.example.appnewsproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PreAuthorize("hasAuthority('ADD_COMMENT')")
    @PostMapping("/add")
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{userId}/{commentId}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long userId, @PathVariable Long commentId){
        ApiResponse apiResponse = commentService.deleteMyComment(userId, commentId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{commentId}")
    public HttpEntity<?> deleteComment(@PathVariable Long commentId){
        ApiResponse apiResponse = commentService.deleteComment(commentId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAllComments(){
        List<CommentResponseDto> allComments = commentService.AllComments();
        return ResponseEntity.ok(allComments);
    }

    @DeleteMapping("/delete/{postId}")
    public HttpEntity<?> deletePostComments(@PathVariable Long postId){
        ApiResponse apiResponse = commentService.deletePostComments(postId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
