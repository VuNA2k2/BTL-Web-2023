package com.example.webs2023.service.jwt;

import com.example.webs2023.dto.jwt.JwtPayload;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface JwtService {
    String createToken(Long userId, String role) throws NoSuchAlgorithmException, InvalidKeyException;
    boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException;
    JwtPayload getPayload(String token);
}
