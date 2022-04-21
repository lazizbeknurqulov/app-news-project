package com.example.appnewsproject.service;

import com.example.appnewsproject.entity.user.Role;
import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.RoleDto;
import com.example.appnewsproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public ApiResponse addRole(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName()))
            return new ApiResponse("Bunday lavozim bor!", false);
        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissions(),
                roleDto.getDescription());
        roleRepository.save(role);
        return new ApiResponse("Saqlandi", true);
    }

    public ApiResponse editRole(RoleDto roleDto, Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setDescription(roleDto.getDescription());
            role.setName(role.getName());
            role.setPermissions(roleDto.getPermissions());
            roleRepository.save(role);
            return new ApiResponse("Successfully edidted", true);
        }
        return new ApiResponse("Bunaqa id li rol topilmadi", false);
    }

    public ApiResponse deleteRole(Long id) {
        roleRepository.deleteById(id);
        return new ApiResponse("Successfully deleted!", true);
    }

    public ApiResponse getRoles() {
        return new ApiResponse("roles", true, roleRepository.findAll());
    }

    public Role getRoleById(Long roleId) {
        Optional<Role> byId = roleRepository.findById(roleId);
        return byId.orElseGet(Role::new);
    }
}
