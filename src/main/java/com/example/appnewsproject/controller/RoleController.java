package com.example.appnewsproject.controller;

import com.example.appnewsproject.aop.CheckPermission;
import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.RoleDto;
import com.example.appnewsproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping("/add")
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @CheckPermission(permission = "hasAnyAuthority('EDIT_ROLE')")
    @PutMapping("{id}")
    public HttpEntity<?> editRole(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id){
        ApiResponse apiResponse = roleService.editRole(roleDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_ROLE')")
    @PostMapping("{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_ROLE')")
    @GetMapping("/get")
    public HttpEntity<?> getRoles(){
        ApiResponse apiResponse = roleService.getRoles();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
