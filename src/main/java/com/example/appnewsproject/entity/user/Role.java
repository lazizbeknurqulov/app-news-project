package com.example.appnewsproject.entity.user;

import com.example.appnewsproject.entity.template.AbsEntity;
import com.example.appnewsproject.entity.user.enums.Permission;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Permission> permissions;

    @Column(columnDefinition = "text")
    private String description;

}
