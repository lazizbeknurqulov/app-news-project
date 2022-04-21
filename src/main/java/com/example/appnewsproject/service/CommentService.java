package com.example.appnewsproject.service;

import com.example.appnewsproject.entity.comment.Comment;
import com.example.appnewsproject.entity.post.Post;
import com.example.appnewsproject.entity.user.User;
import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.CommentDto;
import com.example.appnewsproject.payload.CommentPostDto;
import com.example.appnewsproject.payload.CommentResponseDto;
import com.example.appnewsproject.repository.CommentRepository;
import com.example.appnewsproject.repository.PostRepository;
import com.example.appnewsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> byId = postRepository.findById(commentDto.getPostId());

        if (byId.isEmpty()) {
            return new ApiResponse("Post id topilmadi", false);
        }

        Post post = byId.get();
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return new ApiResponse("Comment added", true);
    }

    public String getUsername(Long userId) {

        Optional<User> byId = userRepository.findById(userId);

        if (byId.isEmpty()) {
            throw new UsernameNotFoundException("User id not found");
        }

        return byId.get().getUsername();
    }

    public List<CommentResponseDto> AllComments() {

        List<Comment> commentList = commentRepository.findAll();
        List<CommentResponseDto> comments = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentResponseDto commentResponseDto = new CommentResponseDto();
            commentResponseDto.setUsername(getUsername(comment.getCreatedBy()));
            commentResponseDto.setText(comment.getText());
            commentResponseDto.setPostId(comment.getPost().getId());
            comments.add(commentResponseDto);
        }
        return comments;
    }

    public List<CommentPostDto> getPostComments(Long postId) {

        List<CommentPostDto> commentPostDtoList = new ArrayList<>();

        for (CommentResponseDto comment : AllComments()) {
            if (comment.getPostId().equals(postId)) {
                CommentPostDto commentPostDto = new CommentPostDto();
                commentPostDto.setText(comment.getText());
                commentPostDto.setUsername(comment.getUsername());
                commentPostDtoList.add(commentPostDto);
            }
        }
        return commentPostDtoList;
    }

    public ApiResponse deleteMyComment(Long userId, Long commentId) {

        Comment comment = commentRepository.findByCreatedByAndId(userId, commentId);

        if (comment == null) {
            return new ApiResponse("Comment not found!", false);
        }

        commentRepository.delete(comment);
        return new ApiResponse("Successfully deleted", true);
    }

    public ApiResponse deleteComment(Long commentId) {

        Optional<Comment> byId = commentRepository.findById(commentId);


        if (byId.isEmpty()) {
            return new ApiResponse("Comment not found!", false);
        }

        Comment comment = byId.get();

        commentRepository.delete(comment);
        return new ApiResponse("Successfully deleted", true);
    }

    public ApiResponse deletePostComments(Long postId){
        List<Comment> comments = commentRepository.findByPost_Id(postId);

        if(comments.size() == 0){
            return new ApiResponse("Bu postda commentlar mavjud emas", false);
        }

        commentRepository.deleteAll(comments);
        return new ApiResponse("Postga tegishli barcha commentlar o'chirildi", true);
    }

}
