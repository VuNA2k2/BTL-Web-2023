package com.example.webs2023.service.image;

import com.example.webs2023.dto.image.ImageInput;
import com.example.webs2023.dto.image.ImageOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    List<ImageOutput> getImageByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    ImageOutput createImage(ImageInput imageInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    void deleteImage(Long id) throws SQLException;
}
