package com.example.appnewsproject.aop;

import com.example.appnewsproject.entity.user.User;
import com.example.appnewsproject.exceptions.ForbiddenException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if(authority.getAuthority().equals(checkPermission.permission())){
                     exist = true;
                     break;
            }
        }
        if(!exist){
            throw new ForbiddenException(checkPermission.permission(), "Ruxsat yo'q");
        }
    }
}
