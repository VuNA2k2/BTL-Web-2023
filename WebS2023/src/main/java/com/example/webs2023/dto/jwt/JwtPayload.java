package com.example.webs2023.dto.jwt;

public class JwtPayload {
    private Long userId;
    private String role;
    private Long exp;

    public JwtPayload() {
    }

    public JwtPayload(Long userId, String role, Long exp) {
        this.userId = userId;
        this.role = role;
        this.exp = exp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
}
