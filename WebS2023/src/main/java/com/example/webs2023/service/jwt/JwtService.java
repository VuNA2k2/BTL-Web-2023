package com.example.webs2023.service.jwt;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface JwtService {
    public String createToken(Long userId, String role) throws NoSuchAlgorithmException, InvalidKeyException;
    public boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException;
}
