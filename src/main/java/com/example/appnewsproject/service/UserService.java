package com.example.appnewsproject.service;

import com.example.appnewsproject.entity.user.Role;
import com.example.appnewsproject.entity.user.User;
import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.GetUsersDto;
import com.example.appnewsproject.payload.RoleDto;
import com.example.appnewsproject.repository.RoleRepository;
import com.example.appnewsproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<GetUsersDto> getUsers(){
        List<User> userList = userRepository.findAll();

        List<GetUsersDto> usersDtoList = new ArrayList<>();
        for (User user : userList) {
            if(user.getRole().getName().equals("USER")){
                RoleDto roleDto = new RoleDto();
                GetUsersDto getAllUsersDto = new GetUsersDto();
                getAllUsersDto.setUsername(user.getUsername());
                getAllUsersDto.setPassword(user.getPassword());
                roleDto.setName(user.getRole().getName());
                roleDto.setDescription(user.getRole().getDescription());
                roleDto.setPermissions(user.getRole().getPermissions());
                getAllUsersDto.setRole(roleDto);
                getAllUsersDto.setFullName(user.getFullName());
                usersDtoList.add(getAllUsersDto);
            }
        }

        return usersDtoList;

    }

    public ApiResponse addAdmin(Long userId){
        Optional<Role> admin = roleRepository.findByName("ADMIN");

        if (admin.isEmpty()) {
            return new ApiResponse("Bunaqa name li admin topilmadi!", true);
        }

        Optional<User> optionalUser = userRepository.findByRoleNameAndId("USER",userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(admin.get());
            userRepository.save(user);
            return new ApiResponse("Successfully finished", true);
        }
        return new ApiResponse("Bunaqa user topilmadi", false);
    }

    public ApiResponse addUser(Long userId){

        Optional<Role> user1 = roleRepository.findByName("USER");

        if (user1.isEmpty()) {
            return new ApiResponse("Bunaqa name li user topilmadi!", true);
        }

        Optional<User> optionalUser = userRepository.findByRoleNameAndId("ADMIN",userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setRole(user1.get());
            userRepository.save(user);
            return new ApiResponse("User added!", true);
        }

        return new ApiResponse("Bunaqa admin topilmadi", false);
    }

    public ApiResponse getAllUsers(){
        return new ApiResponse("allUsers", true, userRepository.findAll());
    }

}
