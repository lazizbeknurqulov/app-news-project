package com.example.appnewsproject.service;

import com.example.appnewsproject.entity.post.Post;
import com.example.appnewsproject.payload.*;
import com.example.appnewsproject.repository.CommentRepository;
import com.example.appnewsproject.repository.PostRepository;
import com.example.appnewsproject.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;

    public ApiResponse addPost(PostDto postDto){
        Post post = new Post();
        Date date = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String url = AppConstants.BASE_URL + "news/" + simpleDateFormat.format(date) + "/"  + postDto.getTitle();
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        post.setUrl(url);

        postRepository.save(post);
        return new ApiResponse("Successfully added!", true);
    }
    public ApiResponse getPosts(){
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setText(post.getText());
            postResponseDto.setUrl(post.getUrl());
            postResponseDto.setTitle(post.getTitle());
            List<CommentPostDto> postComments = commentService.getPostComments(post.getId());
            postResponseDto.setComments(postComments);
            postResponseDtoList.add(postResponseDto);
        }
        return new ApiResponse("posts", true, postResponseDtoList);
    }
}
