package com.example.webs2023.base;

public class BaseService<E, T> {
    private static BaseService instance;

    private BaseRepository<E, T> repository;

    private BaseService() {

    }
    public static BaseService getInstance() {
        if(instance == null) {
            instance = new BaseService();
        }
        return instance;
    }
}
