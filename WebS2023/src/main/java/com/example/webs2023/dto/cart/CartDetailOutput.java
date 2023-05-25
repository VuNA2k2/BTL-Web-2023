package com.example.webs2023.dto.cart;

import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;


import java.util.List;

public class CartDetailOutput extends CartOutput {
    private List<CartRefProductOutput> products;
    private Long totalMoney;

    public CartDetailOutput() {
    }

    public CartDetailOutput(Long id, Long userId, List<CartRefProductOutput> productOutputs) {
        super(id, userId);
        this.products = productOutputs;
    }

    public CartDetailOutput(Long id, Long userId, List<CartRefProductOutput> productOutputs, Long totalMoney) {
        super(id, userId);
        this.products = productOutputs;
        this.totalMoney = totalMoney;
    }

    public CartDetailOutput(Long id, Long userId) {
        super(id, userId);
    }

    public List<CartRefProductOutput> getProducts() {
        return products;
    }

    public void setProducts(List<CartRefProductOutput> products) {
        this.products = products;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }
}
