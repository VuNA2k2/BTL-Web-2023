package com.example.webs2023.service.auth;

import com.example.webs2023.dto.login.LoginInput;
import com.example.webs2023.dto.login.LoginOutput;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;

import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface AuthService {


    LoginOutput login(LoginInput loginInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException, InvalidKeyException;

    UserOutput register(UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

}
