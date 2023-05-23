package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.entity.CartEntity;
import com.example.webs2023.repository.CartRepository;

public class CartServiceImpl extends BaseService<CartEntity, Long, CartInput, CartOutput> implements CartService {

    public CartServiceImpl(CartRepository cartRepository) {
        this.repository = cartRepository;
        this.mapper = new CartMapper(CartEntity.class, CartInput.class, CartOutput.class);
    }
    @Override
    public CartOutput getCartById(Long id) {
        return null;
    }
}
