package com.example.webs2023.service.rate;

import com.example.webs2023.dto.rate.RateInput;
import com.example.webs2023.dto.rate.RateOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface RateService {
    RateOutput createRate(Long userId, RateInput rateInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
