package com.example.webs2023.service.jwt;

import com.example.webs2023.dto.jwt.JwtPayload;
import com.example.webs2023.exception.TokenExpiredException;
import com.example.webs2023.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Calendar;

public class JwtServiceImpl implements JwtService {

    private final Gson gson = new GsonBuilder().create();
    private static final String SECRET = "WebS2023";
    private static final String ALGORITHM = "HmacSHA256";
    private static final Long EXPIRED_TIME = 1000L * 60 * 60 * 3;
    private final UserRepository userRepository;

    public JwtServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public String createToken(Long userId, String role) throws NoSuchAlgorithmException, InvalidKeyException {
        Long now = Calendar.getInstance().getTimeInMillis();
        JwtPayload jwtPayload = new JwtPayload(userId, role, now + EXPIRED_TIME);
        String prePayload = gson.toJson(jwtPayload);
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        String encodedPayload = Base64.getUrlEncoder().encodeToString(prePayload.getBytes(StandardCharsets.UTF_8));
        String signature = this.sign(header, encodedPayload);
        return header + "." + encodedPayload + "." + signature;
    }

    public boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException, SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }
        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];
        String calculatedSignature = sign(header, payload);
        JwtPayload jwtPayload = getPayload(token);
        if (jwtPayload.getExp() < Calendar.getInstance().getTimeInMillis()) {
            throw new TokenExpiredException();
        }
        if(!jwtPayload.getRole().equals("ADMIN") && !jwtPayload.getRole().equals("USER")) {
            return false;
        }
        if(userRepository.getById(jwtPayload.getUserId()) == null) {
            return false;
        }
        return signature.equals(calculatedSignature);
    }

    public JwtPayload getPayload(String token) {
        String[] parts = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
        return gson.fromJson(payload, JwtPayload.class);
    }

    private String sign(String header, String payload) throws NoSuchAlgorithmException, InvalidKeyException {
        String data = header + "." + payload;
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(secretKeySpec);
        byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(signatureBytes);
    }
}
