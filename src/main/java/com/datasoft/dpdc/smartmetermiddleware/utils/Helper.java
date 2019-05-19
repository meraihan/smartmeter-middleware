package com.datasoft.dpdc.smartmetermiddleware.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by rayhan on 9/20/18.
 */
public class Helper {
    public static PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
}
