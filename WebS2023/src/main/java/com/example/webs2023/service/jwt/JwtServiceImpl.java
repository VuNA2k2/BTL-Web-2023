package com.example.webs2023.service.jwt;

import com.example.webs2023.dto.jwt.JwtPayload;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Calendar;

public class JwtServiceImpl implements JwtService {

    private Gson gson = new GsonBuilder().create();
    private static final String SECRET = "WebS2023";
    private static final String ALGORITHM = "HmacSHA256";
    private static final Long EXPIRED_TIME = 1000L * 60 * 60 * 3;
    public String createToken(Long userId, String role) throws NoSuchAlgorithmException, InvalidKeyException {
        Long now = Calendar.getInstance().getTimeInMillis();
        JwtPayload jwtPayload = new JwtPayload(userId, role, now + EXPIRED_TIME);
        String prePayload = gson.toJson(jwtPayload);
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        String encodedPayload = Base64.getUrlEncoder().encodeToString(prePayload.getBytes(StandardCharsets.UTF_8));
        String signature = this.sign(header, encodedPayload);
        return header + "." + encodedPayload + "." + signature;
    }

    public boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }

        String header = parts[0];
        String payload = parts[1];
        String signature = parts[2];
        String decode = new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
        System.out.println(decode);
        String calculatedSignature = sign(header, payload);
        return signature.equals(calculatedSignature);
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
