package com.example.appnewsproject.payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {

    private String title;

    private String text;

    private String url;

    List<CommentPostDto> comments;


}
