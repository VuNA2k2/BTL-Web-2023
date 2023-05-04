package com.example.webs2023.service.auth;


import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.dto.login.LoginOutput;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.service.jwt.JwtService;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.sql.SQLException;

public class AuthService {
    UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public LoginOutput login(LoginInput loginInput) {
        try {
            UserEntity userEntity = userRepository.exitsWithUsernameAndPassword(loginInput.getUsername(), loginInput.getPassword());
            if(userEntity != null) {
                String token = JwtService.createToken(userEntity.getId(), userEntity.getRole());
                return new LoginOutput(token);
            } else {
                return null;
            }
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
