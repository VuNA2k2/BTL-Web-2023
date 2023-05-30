package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends BaseRepository<UserEntity, Long> {

    public UserRepository(Class<UserEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<UserEntity> getUserByDateOfBirth(String dateOfBirth) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return rawQuery("SELECT * FROM users WHERE date_of_birth='" +dateOfBirth+ "'");

    }

    public UserEntity exitsWithUsernameAndPassword(String username, String password) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'").get(0);
    }

    public UserEntity getByUsername(String username) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM users WHERE username='" + username + "'").get(0);
    }

    public UserEntity getByEmail(String email) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM users WHERE email='" + email + "'").get(0);
    }

    public UserEntity getByPhone(String phone) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM users WHERE phone='" + phone + "'").get(0);
    }
}
