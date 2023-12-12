package com.data.tools.api.util;

import com.data.tools.api.entity.User;
import com.data.tools.api.exceptions.Exceptions;
import com.data.tools.api.exceptions.GlobalException;
import com.data.tools.api.security.UesrDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserHelper {

    public static boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }

    public static User getUser() {
        if(isAuthenticated()) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal instanceof UesrDetailsImpl userService) {
                return userService.getUser();
            }
        }
        throw GlobalException.throwEx(Exceptions.USER_NOT_LOGIN, "User not login");
    }
}
