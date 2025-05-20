package com.javaee.ecard;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder().encode("password");
        System.out.println(password);
    }
}
