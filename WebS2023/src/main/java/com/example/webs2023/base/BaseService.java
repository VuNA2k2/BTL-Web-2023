package com.example.webs2023.base;

public abstract class BaseService<E, T, I, O> {
    protected BaseRepository<E, T> repository;

    protected BaseMapper<E, I, O> mapper;

    protected BaseService() {

    }


}
