package com.example.webs2023.service.rate;

import com.example.webs2023.dto.rate.AvgRateOutput;
import com.example.webs2023.dto.rate.RateInput;
import com.example.webs2023.dto.rate.RateOutput;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface RateService {
    RateOutput createRate(Long userId, RateInput rateInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    List<RateOutput> getListRateByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    AvgRateOutput avgRateByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
