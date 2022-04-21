package com.example.appnewsproject.controller;

import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.PostDto;
import com.example.appnewsproject.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping("/add")
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @GetMapping("/get")
    public HttpEntity<?> getPosts(){
        ApiResponse apiResponse = postService.getPosts();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
