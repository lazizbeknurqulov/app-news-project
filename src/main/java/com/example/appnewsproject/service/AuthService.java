package com.example.appnewsproject.service;
import com.example.appnewsproject.entity.user.Role;
import com.example.appnewsproject.entity.user.User;
import com.example.appnewsproject.exceptions.ResourceNotFoundException;
import com.example.appnewsproject.payload.ApiResponse;
import com.example.appnewsproject.payload.RegisterDto;
import com.example.appnewsproject.repository.RoleRepository;
import com.example.appnewsproject.repository.UserRepository;
import com.example.appnewsproject.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ApiResponse registerUser(RegisterDto registerDto) {

        if(!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Parollar mos kelmadi", false);
        }

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse("User already registered", false);
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role role = roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", AppConstants.USER));
        user.setRole(role);
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz", true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username + " topilmadi"));
    }
}
