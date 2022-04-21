package com.example.appnewsproject.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetUsersDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private RoleDto role;


}
