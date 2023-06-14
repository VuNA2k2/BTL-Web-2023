package com.example.webs2023.repository;

import com.example.webs2023.base.BaseRepository;
import com.example.webs2023.entity.ImageEntity;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ImageRepository extends BaseRepository<ImageEntity, Long> {

    public ImageRepository(Class<ImageEntity> entityClass) throws SQLException, ClassNotFoundException {
        super(entityClass);
    }

    public List<ImageEntity> getImagesByRefIdAndRefType(Long refId, String refType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return rawQuery("SELECT * FROM images WHERE ref_id = " + refId + " AND ref_type = '" + refType + "'");
    }
}
