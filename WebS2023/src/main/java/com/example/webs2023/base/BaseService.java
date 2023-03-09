package com.example.webs2023.base;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseService<E, T, I, O> {
    protected BaseRepository<E, T> repository;

    protected BaseMapper<E, I, O> mapper;

    protected BaseService() {

    }

    public O getById(T id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(repository.getById(id));
    }

    public O save(I input) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return mapper.getOutputFromEntity(repository.save(mapper.getEntityFromInput(input)));
    }

    public List<O> getAll() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return (List<O>) repository.getAll().stream().map(mapper::getOutputFromEntity).toList();
    }


}
