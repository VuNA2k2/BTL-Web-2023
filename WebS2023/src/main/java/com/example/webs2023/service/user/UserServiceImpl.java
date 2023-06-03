package com.example.webs2023.service.user;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.user.UserInput;
import com.example.webs2023.dto.user.UserOutput;
import com.example.webs2023.entity.UserEntity;
import com.example.webs2023.repository.UserRepository;
import com.example.webs2023.utils.BcryptUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends BaseService<UserEntity, Long, UserInput, UserOutput> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
        mapper = new UserMapper(UserEntity.class, UserInput.class, UserOutput.class);
    }

    @Override
    public UserOutput getUserById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getById(id);
    }

    @Override
    public UserOutput getUserByUsername(String username) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(((UserRepository) repository).getByUsername(username));
    }

    @Override
    public UserOutput exitsWithUsernameAndPassword(String username, String password) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        UserEntity userEntity = ((UserRepository) repository).getByUsername(username);
        if (BcryptUtils.checkPassword(password, userEntity.getPassword())) {
            return mapper.getOutputFromEntity(userEntity);
        }
        return null;
    }

    @Override
    public UserOutput saveUser(UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        userInput.setPassword(BcryptUtils.hashPassword(userInput.getPassword()));
        return save(userInput);
    }

    @Override
    public UserOutput updateUser(Long id, UserInput userInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (userInput.getPassword() == null) {
            UserEntity userEntity = repository.getById(id);
            userInput.setPassword(userEntity.getPassword());
        }
        userInput.setPassword(BcryptUtils.hashPassword(userInput.getPassword()));
        return updateById(id, userInput);
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        deleteById(id);
    }

    @Override
    public UserOutput getUserByEmail(String email) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(((UserRepository) repository).getByEmail(email));
    }

    @Override
    public UserOutput getUserByPhone(String phone) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(((UserRepository) repository).getByPhone(phone));
    }

    @Override
    public List<UserOutput> getUserByAddress(String address) {
        return null;
    }

    @Override
    public List<UserOutput> getUserByRole(String role) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<UserEntity> userEntities = repository.getAll("WHERE role = '" + role + "'");
        return userEntities.stream().map(userEntity -> (UserOutput) mapper.getOutputFromEntity(userEntity)).toList();
    }

    @Override
    public List<UserOutput> getAllUsers() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getAll();
    }


}
