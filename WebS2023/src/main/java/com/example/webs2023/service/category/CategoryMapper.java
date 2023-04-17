package com.example.webs2023.service.category;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.category.CategoryInput;
import com.example.webs2023.dto.category.CategoryOutput;
import com.example.webs2023.entity.CategoryEntity;

public class CategoryMapper extends BaseMapper<CategoryEntity, CategoryInput, CategoryOutput> {
    public CategoryMapper(Class<CategoryEntity> entityClass, Class<CategoryInput> inputDtoClass, Class<CategoryOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
