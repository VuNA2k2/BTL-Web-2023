package com.example.webs2023.service.user;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserService extends BaseService<UserEntity, Long, UserInput, UserOutput> {

    public UserService(UserRepository repository) {
        super();
        this.repository = repository;
        mapper = new UserMapper(UserEntity.class, UserInput.class, UserOutput.class);
    }

    public UserOutput exitsWithUsernameAndPassword(String username, String password) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UserEntity userEntity = ((UserRepository) repository).exitsWithUsernameAndPassword(username, password);
        return mapper.getOutputFromEntity(userEntity);
    }
}
