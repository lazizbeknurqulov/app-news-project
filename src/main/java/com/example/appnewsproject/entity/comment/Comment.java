package com.example.appnewsproject.entity.comment;

import com.example.appnewsproject.entity.post.Post;
import com.example.appnewsproject.entity.template.AbsEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbsEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

}
