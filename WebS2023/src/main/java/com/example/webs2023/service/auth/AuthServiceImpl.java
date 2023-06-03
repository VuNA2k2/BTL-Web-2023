package com.example.webs2023.service.auth;


import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.dto.login.LoginOutput;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.service.jwt.JwtService;
import com.example.webs2023.service.user.UserService;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {
    UserService userService;
    JwtService jwtService;

    public AuthServiceImpl(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public LoginOutput login(LoginInput loginInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException, InvalidKeyException {
        UserOutput userOutput = userService.exitsWithUsernameAndPassword(loginInput.getUsername(), loginInput.getPassword());
        if (userOutput != null) {
            String token = jwtService.createToken(userOutput.getId(), userOutput.getRole());
            return new LoginOutput(token);
        } else {
            return null;
        }

    }

    @Override
    public UserOutput register(UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return userService.saveUser(userInput);
    }
}
