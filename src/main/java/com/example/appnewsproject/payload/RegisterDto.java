package com.example.appnewsproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotNull(message = "FullName bo'sh bo'lolmaydi")
    @Size(min = 5, message="Name must be at least 5 characters long")
    private String fullName;

    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "Password bo'sh bo'lmsin")
    private String password;

    @NotNull(message = "Parol takrori bo'sh bo'lmasin!")
    private String prePassword;


}
