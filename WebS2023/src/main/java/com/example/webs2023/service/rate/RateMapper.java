package com.example.webs2023.service.rate;

import com.example.webs2023.base.BaseMapper;
import com.example.webs2023.dto.rate.RateInput;
import com.example.webs2023.dto.rate.RateOutput;
import com.example.webs2023.entity.RateEntity;

public class RateMapper extends BaseMapper<RateEntity, RateInput, RateOutput> {
    public RateMapper(Class<RateEntity> entityClass, Class<RateInput> inputDtoClass, Class<RateOutput> ouputDtoClass) {
        super(entityClass, inputDtoClass, ouputDtoClass);
    }
}
