package com.itkachuk.pa.mvc.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 * User: itkachuk
 * Date: 11/18/2015 11:18 AM
 */
public class AuthUtils {

    //static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    //get logged in username
    public static String getLoggedUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
