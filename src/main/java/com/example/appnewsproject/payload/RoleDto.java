package com.example.appnewsproject.payload;

import com.example.appnewsproject.entity.user.enums.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {

    @NotBlank
    private String name;

    @JsonIgnore
    private String description;

    @JsonIgnore
    @NotEmpty
    private List<Permission> permissions;

}
