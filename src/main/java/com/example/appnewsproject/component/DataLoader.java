package com.example.appnewsproject.component;

import com.example.appnewsproject.entity.user.Role;
import com.example.appnewsproject.entity.user.User;
import com.example.appnewsproject.entity.user.enums.Permission;
import com.example.appnewsproject.repository.RoleRepository;
import com.example.appnewsproject.repository.UserRepository;
import com.example.appnewsproject.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args)  {

        if (initialMode.equals("always")) {
            Permission[] permissions = Permission.values();
            Role super_user = roleRepository.save(new Role(
                    AppConstants.SUPER_ADMIN,
                    List.of(permissions),
                    "Sistema egasi"
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    List.of(Permission.ADD_COMMENT,
                            Permission.EDIT_COMMENT,
                            Permission.DELETE_MY_COMMENT),
                    "Oddiy foydalanuvchi"
            ));

            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    List.of(
                            Permission.ADD_ROLE,
                            Permission.EDIT_ROLE,
                            Permission.DELETE_ROLE,
                            Permission.VIEW_ROLE,

                            Permission.ADD_POST,
                            Permission.EDIT_POST,
                            Permission.DELETE_POST,

                            Permission.ADD_COMMENT,
                            Permission.EDIT_COMMENT,
                            Permission.DELETE_COMMIT,
                            Permission.DELETE_MY_COMMENT
                    ),
                    "Admin"
            ));
        }
    }
}
