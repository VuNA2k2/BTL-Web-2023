package com.example.webs2023.dto.login;

public class LoginOutput {
    private String token;

    public LoginOutput() {

    }

    public LoginOutput(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
