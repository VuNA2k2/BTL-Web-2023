package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.dto.cart.CartRefProductOutput;
import com.example.webs2023.entity.CartEntity;
import com.example.webs2023.repository.CartRepository;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
public class CartServiceImpl extends BaseService<CartEntity, Long, CartInput, CartOutput> implements CartService {
    private  final CartRefProductService cartRefProductService;
    public CartServiceImpl(CartRepository cartRepository, CartRefProductService cartRefProductService) {
        this.repository = cartRepository;
        this.cartRefProductService = cartRefProductService;
        this.mapper = new CartMapper(CartEntity.class, CartInput.class, CartOutput.class);
    }

    @Override
    public CartOutput getCartById(Long id) {
        try {
            return getById(id);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CartOutput getCartByUserId(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

            CartOutput cartOutput = mapper.getOutputFromEntity(((CartRepository) repository).getCartByUserId(userId));
            List<CartRefProductOutput> cartRefProductOutputs = cartRefProductService.getCartRefProductsByCartId(cartOutput.getId());
            cartOutput.setProducts(cartRefProductOutputs);
            return cartOutput;

    }

    @Override
    public CartOutput saveCart(CartInput cartInput) {
        try {
            return save(cartInput);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CartOutput updateCart(Long id, CartInput cartInput) {
        try {
            return updateById(id, cartInput);
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCart(Long id) {
        try {
            deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
