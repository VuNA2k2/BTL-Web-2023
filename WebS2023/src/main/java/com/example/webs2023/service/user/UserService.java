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

    public UserOutput getUserById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(repository.getById(id));
    }

    public UserOutput save(UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(repository.save(mapper.getEntityFromInput(userInput)));
    }

    public List<UserOutput> getUserByDateOfBirth(String dateOfBirth) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ((UserRepository) repository).getUserByDateOfBirth("20-09-2002").stream().map((e) -> (UserOutput) mapper.getOutputFromEntity(e)).toList();
    }
}
