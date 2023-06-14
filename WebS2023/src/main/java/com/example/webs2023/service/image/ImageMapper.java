package com.example.webs2023.service.image;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.image.ImageInput;
import com.example.webs2023.dto.image.ImageOutput;
import com.example.webs2023.entity.ImageEntity;

public class ImageMapper extends BaseMapper<ImageEntity, ImageInput, ImageOutput> {
    public ImageMapper(Class<ImageEntity> entityClass, Class<ImageInput> inputDtoClass, Class<ImageOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
