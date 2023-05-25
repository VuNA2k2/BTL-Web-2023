package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart.CartDetailOutput;
import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.dto.cart_ref_product.CartRefProductOutput;
import com.example.webs2023.entity.CartEntity;
import com.example.webs2023.repository.CartRepository;
import com.example.webs2023.service.cart_ref_product.CartRefProductService;
import com.example.webs2023.service.product.ProductService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl extends BaseService<CartEntity, Long, CartInput, CartOutput> implements CartService {
    private final CartRefProductService cartRefProductService;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, CartRefProductService cartRefProductService, ProductService productService) {
        this.repository = cartRepository;
        this.cartRefProductService = cartRefProductService;
        this.productService = productService;
        this.mapper = new CartMapper(CartEntity.class, CartInput.class, CartOutput.class);
    }


    @Override
    public CartDetailOutput getLeastCart(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getDetailCartFromCartEntity(repository.getAll("ORDER BY id DESC LIMIT 1").get(0));
    }

    @Override
    public CartDetailOutput getDetailCartFromCartEntity(CartEntity cartEntity) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartOutput cartOutput = mapper.getOutputFromEntity(cartEntity);
        CartDetailOutput cartDetailOutput = new CartDetailOutput(cartOutput.getId(), cartOutput.getUserId());
        List<CartRefProductOutput> cartRefProductOutputList = cartRefProductService.getCartsRefProductByCartId(cartDetailOutput.getId());
        Long totalMoney = cartRefProductOutputList.stream().mapToLong((e) -> e.getProduct().getPrice() * e.getQuantity()).sum();
        cartDetailOutput.setTotalMoney(totalMoney);
        cartDetailOutput.setProducts(cartRefProductOutputList);
        return cartDetailOutput;
    }
}
