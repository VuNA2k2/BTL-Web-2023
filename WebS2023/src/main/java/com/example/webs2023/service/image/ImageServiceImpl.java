package com.example.webs2023.service.image;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.image.ImageInput;
import com.example.webs2023.dto.image.ImageOutput;
import com.example.webs2023.entity.ImageEntity;
import com.example.webs2023.repository.ImageRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ImageServiceImpl extends BaseService<ImageEntity, Long, ImageInput, ImageOutput> implements ImageService {
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.repository = imageRepository;
        this.mapper = new ImageMapper(ImageEntity.class, ImageInput.class, ImageOutput.class);
    }

    @Override
    public List<ImageOutput> getImageByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<ImageEntity> imageEntities = ((ImageRepository) repository).getImagesByRefIdAndRefType(productId, "PRODUCT");
        List<ImageOutput> imageOutputs = imageEntities.stream().map(imageEntity -> (ImageOutput) mapper.getOutputFromEntity(imageEntity)).toList();
        return imageOutputs;
    }

    @Override
    public ImageOutput createImage(ImageInput imageInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ImageEntity imageEntity = mapper.getEntityFromInput(imageInput);
        ImageEntity createdImageEntity = repository.save(imageEntity);
        return mapper.getOutputFromEntity(createdImageEntity);
    }
}
