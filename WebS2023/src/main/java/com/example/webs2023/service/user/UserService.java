package com.example.webs2023.service.user;

import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    UserOutput getUserById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    UserOutput getUserByUsername(String username) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    UserOutput exitsWithUsernameAndPassword(String username, String password) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    UserOutput saveUser(UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    UserOutput updateUser(Long id, UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    void deleteUser(Long id) throws SQLException;
    UserOutput getUserByEmail(String email);
    UserOutput getUserByPhone(String phone);
    List<UserOutput> getUserByAddress(String address);
    List<UserOutput> getUserByRole(String role);
}
