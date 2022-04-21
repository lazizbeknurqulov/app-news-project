package com.example.appnewsproject.controller;

import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.GetUsersDto;
import com.example.appnewsproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping("/get")
    public HttpEntity<?> getUsers(){
        List<GetUsersDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ADD_ADMIN')")
    @GetMapping("/user/{id}")
    public HttpEntity<?> addAdmin(@PathVariable Long id){
        ApiResponse apiResponse = userService.addAdmin(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('ADD_USER')")
    @GetMapping("/admin/{id}")
    public HttpEntity<?> addUser(@PathVariable Long id){
        ApiResponse apiResponse = userService.addUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('VIEW_ALL_USERS')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAllUsers(){
        ApiResponse apiResponse = userService.getAllUsers();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }




}
