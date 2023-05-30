package com.example.webs2023.service.rate;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.image.ImageInput;
import com.example.webs2023.dto.image.ImageOutput;
import com.example.webs2023.dto.rate.AvgRateOutput;
import com.example.webs2023.dto.rate.RateInput;
import com.example.webs2023.dto.rate.RateOutput;
import com.example.webs2023.entity.RateEntity;
import com.example.webs2023.repository.ProductInOrderRepository;
import com.example.webs2023.repository.RateRepository;
import com.example.webs2023.service.image.ImageService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RateServiceImpl extends BaseService<RateEntity, Long, RateInput, RateOutput> implements RateService {
    private final ProductInOrderRepository productInOrderRepository;
    private final ImageService imageService;

    public RateServiceImpl(RateRepository repository, ProductInOrderRepository productInOrderRepository, ImageService imageService) {
        this.repository = repository;
        this.mapper = new RateMapper(RateEntity.class, RateInput.class, RateOutput.class);
        this.productInOrderRepository = productInOrderRepository;
        this.imageService = imageService;
    }

    @Override
    public RateOutput createRate(Long userId, RateInput rateInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (productInOrderRepository.existsByUserOrderAndId(userId, rateInput.getProductInOrderId())) {
            return null;
        }
        RateEntity rateEntity = mapper.getEntityFromInput(rateInput);
        rateEntity.setProductInOrderId(productInOrderRepository.getById(rateInput.getProductInOrderId()).getProductId());
        rateEntity.setUserId(userId);
        rateEntity.setCreatedAt(Timestamp.from(Instant.now()));
        rateEntity = repository.save(rateEntity);
        List<ImageOutput> images = new ArrayList<>();
        RateEntity finalRateEntity = rateEntity;
        rateInput.getImages().forEach(image -> {
            try {
                ImageInput imageInput = new ImageInput();
                imageInput.setLink(image);
                imageInput.setRefId(finalRateEntity.getId());
                imageInput.setRefType("RATE");
                images.add(imageService.createImage(imageInput));
            } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException throwables) {
                throwables.printStackTrace();
            }
        });
        RateOutput rateOutput = mapper.getOutputFromEntity(rateEntity);
        rateOutput.setImages(images);
        return rateOutput;
    }

    @Override
    public List<RateOutput> getListRateByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<RateEntity> rateEntities = repository.getAll("WHERE product_in_order_id = " + productId);
        List<RateOutput> rateOutputs = new ArrayList<>();
        rateEntities.forEach(rateEntity -> {
            try {
                RateOutput rateOutput = mapper.getOutputFromEntity(rateEntity);
                rateOutput.setImages(imageService.getImageByRateId(rateEntity.getId()));
                rateOutputs.add(rateOutput);
            } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException throwables) {
                throwables.printStackTrace();
            }
        });
        return rateOutputs;
    }

    @Override
    public AvgRateOutput avgRateByProductId(Long productId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<RateEntity> rateEntities = repository.getAll("WHERE product_in_order_id = " + productId);
        AvgRateOutput avgRateOutput = new AvgRateOutput();
        if (rateEntities.size() == 0) {
            avgRateOutput.setAvgRate(0.0);
            avgRateOutput.setCountRate(0L);
            return avgRateOutput;
        }
        double totalRate;
        totalRate = rateEntities.stream().mapToDouble(RateEntity::getStar).sum();
        avgRateOutput.setAvgRate(totalRate / rateEntities.size());
        avgRateOutput.setCountRate((long) rateEntities.size());
        return avgRateOutput;
    }
}
