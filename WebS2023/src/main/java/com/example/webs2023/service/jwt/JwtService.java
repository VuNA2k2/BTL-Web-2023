package com.example.webs2023.service.jwt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Calendar;

public class JwtService {
    private static final String SECRET = "WebS2023";
    private static final String ALGORITHM = "HmacSHA256";
    private static final Long EXPIRED_TIME = 1000L * 60 * 60 * 24 * 7;
    public static String createToken(String payload) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Long now = Calendar.getInstance().getTimeInMillis();
        String prePayload = "{\"id\":\"" + payload + "\" + \"exp\":" + now + EXPIRED_TIME +"}";
        String header = Base64.getUrlEncoder().encodeToString("{\"alg\":\"HS256\",\"typ\":\"JWT\"}".getBytes(StandardCharsets.UTF_8));
        String encodedPayload = Base64.getUrlEncoder().encodeToString(prePayload.getBytes(StandardCharsets.UTF_8));
        String signature = sign(header, encodedPayload);
        return header + "." + encodedPayload + "." + signature;
    }

    public static boolean validateToken(String token) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
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

    private static String sign(String header, String payload) throws NoSuchAlgorithmException, InvalidKeyException {
        String data = header + "." + payload;
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(secretKeySpec);
        byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().encodeToString(signatureBytes);
    }
}
