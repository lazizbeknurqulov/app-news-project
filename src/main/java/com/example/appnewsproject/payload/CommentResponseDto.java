package com.example.appnewsproject.payload;

import lombok.Data;

@Data
public class CommentResponseDto {

    private String username;

    private String text;

    private Long postId;

}
