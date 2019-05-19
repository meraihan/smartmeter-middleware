package com.datasoft.dpdc.smartmetermiddleware.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by rayhan on 7/23/18.
 */
public class GeneratePassword {
    public static void main(String[] args) {
            String password = "aaaaa@";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println(passwordEncoder.encode(password));
    }
}
