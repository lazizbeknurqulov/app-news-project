package com.example.appnewsproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInDto {

    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "Password bo'sh bo'lmsin")
    private String password;


}
