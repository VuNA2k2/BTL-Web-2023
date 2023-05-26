package com.example.webs2023.service.jwt;

import com.example.webs2023.dto.jwt.JwtPayload;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface JwtService {
    String createToken(Long userId, String role) throws NoSuchAlgorithmException, InvalidKeyException;
    boolean validateToken(String token, String requireRole) throws NoSuchAlgorithmException, InvalidKeyException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    JwtPayload getPayload(String token);
}
