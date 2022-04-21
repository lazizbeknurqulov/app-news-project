package com.example.appnewsproject.payload;

import lombok.Data;

@Data
public class CommentDto {

    private String text;

    private Long postId;


}
