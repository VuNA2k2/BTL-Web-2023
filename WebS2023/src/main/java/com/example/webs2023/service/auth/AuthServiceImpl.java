package com.example.webs2023.service.auth;


import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.dto.login.LoginOutput;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.service.jwt.JwtService;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    JwtService jwtService;
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public LoginOutput login(LoginInput loginInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException, InvalidKeyException {

            UserEntity userEntity = userRepository.exitsWithUsernameAndPassword(loginInput.getUsername(), loginInput.getPassword());
            if(userEntity != null) {
                String token = jwtService.createToken(userEntity.getId(), userEntity.getRole());
                return new LoginOutput(token);
            } else {
                return null;
            }

    }
}
