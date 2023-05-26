package com.example.webs2023.service.cart_ref_product;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart_ref_product.CartRefProductInput;
import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.dto.product.ProductOutput;
import com.example.webs2023.entity.CartsRefProductEntity;
import com.example.webs2023.repository.CartRefProductRepository;
import com.example.webs2023.service.product.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartRefProductServiceImpl extends BaseService<CartsRefProductEntity, Long, CartRefProductInput, CartRefProductOutput> implements CartRefProductService {

    private final ProductService productService;
    public CartRefProductServiceImpl(CartRefProductRepository cartRefProductRepository, ProductService productService) {
        this.productService = productService;
        this.repository = cartRefProductRepository;
        this.mapper = new CartRefProductMapper(CartsRefProductEntity.class, CartRefProductInput.class, CartRefProductOutput.class);
    }


    @Override
    public List<CartRefProductOutput> getCartsRefProductByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<CartsRefProductEntity> cartsRefProductEntities = ((CartRefProductRepository) repository).getCartsRefProductByCartId(cartId);
        List<CartRefProductOutput> list = cartsRefProductEntities.stream().map(this::getCartRefProductOutputByCartRefProductEntity).toList();
        return list;
    }

    @Override
    public CartRefProductOutput getCartRefProductOutputByCartRefProductEntity(CartsRefProductEntity cartsRefProductEntity) {
        try {
            CartRefProductOutput cartRefProductOutput = mapper.getOutputFromEntity(cartsRefProductEntity);
            ProductOutput productOutput = productService.getProductById(cartRefProductOutput.getProductId());
            cartRefProductOutput.setProduct(productOutput);
            return cartRefProductOutput;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public CartRefProductOutput create(CartRefProductInput cartRefProductInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartsRefProductEntity cartsRefProductEntity = mapper.getEntityFromInput(cartRefProductInput);
        cartsRefProductEntity = repository.save(cartsRefProductEntity);
        return getCartRefProductOutputByCartRefProductEntity(cartsRefProductEntity);
    }

    @Override
    public Long existsByCartIdAndProductId(Long cartId, Long productId) {
        try {
            return ((CartRefProductRepository) repository).existsByCartIdAndProductId(cartId, productId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CartRefProductOutput update(Long id, CartRefProductInput cartRefProductInput) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartsRefProductEntity cartsRefProductEntity = mapper.getEntityFromInput(cartRefProductInput);
        cartsRefProductEntity.setId(id);
        cartsRefProductEntity = repository.updateById(id, cartsRefProductEntity);
        return getCartRefProductOutputByCartRefProductEntity(cartsRefProductEntity);
    }


}
