package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.base.DependencyInjector;
import com.example.webs2023.dto.cart.CartRefProductInput;
import com.example.webs2023.dto.cart.CartRefProductOutput;
import com.example.webs2023.entity.CartRefProductEntity;
import com.example.webs2023.repository.CartRefProductRepository;
import com.example.webs2023.repository.CartRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartRefProductServiceImpl extends BaseService<CartRefProductEntity, Long, CartRefProductInput, CartRefProductOutput> implements CartRefProductService{


    public CartRefProductServiceImpl(CartRefProductRepository cartRefProductRepository) {
        this.repository = (CartRefProductRepository) cartRefProductRepository;
        this.mapper = new CartRefProductMapper(CartRefProductEntity.class, CartRefProductInput.class, CartRefProductOutput.class);
    }

    @Override
    public CartRefProductOutput getCartRefProductById(Long id) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getById(id);
    }

    @Override
    public List<CartRefProductOutput> getCartRefProductsByCartId(Long cartId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<CartRefProductEntity> entities = ((CartRefProductRepository) repository).getCartRefProductsByCartId(cartId);
        return entities.stream().map((e) -> (CartRefProductOutput) mapper.getOutputFromEntity(e)).toList();
    }

    @Override
    public void addProductToCart(Long cartId, CartRefProductInput cartRefProductInput) {

    }

    @Override
    public CartRefProductOutput updateCartRefProduct(Long id, CartRefProductInput cartRefProductInput) {
        return null;
    }

    @Override
    public void removeProductFromCart(Long id) {

    }
}
