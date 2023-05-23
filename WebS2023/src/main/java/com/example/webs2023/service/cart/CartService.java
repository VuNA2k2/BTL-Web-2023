package com.example.webs2023.service.cart;

import com.example.webs2023.dto.cart.CartOutput;

public interface CartService {
    CartOutput getCartById(Long id);
}
