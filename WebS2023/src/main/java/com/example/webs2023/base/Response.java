package com.example.webs2023.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Response<T> {

    private static final Gson GSON = new GsonBuilder().create();
    private String code;
    private String message;
    private T data;

    public String toJson() {
        return GSON.toJson(this);
    }

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
