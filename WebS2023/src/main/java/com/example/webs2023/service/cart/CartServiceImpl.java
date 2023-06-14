package com.example.webs2023.service.cart;

import com.example.webs2023.base.BaseService;
import com.example.webs2023.dto.cart.CartDetailOutput;
import com.example.webs2023.dto.cart.CartInput;
import com.example.webs2023.dto.cart.CartOutput;
import com.example.webs2023.dto.cart.ProductInCartRequest;
import com.example.webs2023.dto.cart_ref_product.CartRefProductInput;
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

    public CartServiceImpl(CartRepository cartRepository, CartRefProductService cartRefProductService, ProductService productService) {
        this.repository = cartRepository;
        this.cartRefProductService = cartRefProductService;
        this.mapper = new CartMapper(CartEntity.class, CartInput.class, CartOutput.class);
    }


    @Override
    public CartDetailOutput getLeastCart(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return getDetailCartFromCartEntity(repository.getAll("WHERE user_id = " + userId + " ORDER BY id DESC LIMIT 1").get(0));
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

    @Override
    public CartDetailOutput addProductToCart(ProductInCartRequest productInCartRequest, Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartEntity cartEntity = repository.getAll("WHERE user_id = " + userId + " ORDER BY id DESC LIMIT 1").get(0);
        Long id = cartRefProductService.existsByCartIdAndProductId(cartEntity.getId(), productInCartRequest.getProductId());
        if (id != null) {
            CartRefProductInput cartRefProductInput = new CartRefProductInput(cartEntity.getId(), productInCartRequest.getProductId(), productInCartRequest.getQuantity() + 1);
            cartRefProductService.update(id, cartRefProductInput);
            return getDetailCartFromCartEntity(cartEntity);
        }
        CartRefProductInput cartRefProductInput = new CartRefProductInput(cartEntity.getId(), productInCartRequest.getProductId(), productInCartRequest.getQuantity());
        cartRefProductService.create(cartRefProductInput);
        return getDetailCartFromCartEntity(cartEntity);
    }

    @Override
    public CartDetailOutput update(ProductInCartRequest productInCartRequest, Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartEntity cartEntity = repository.getAll("WHERE user_id = " + userId + " ORDER BY id DESC LIMIT 1").get(0);
        Long id = cartRefProductService.existsByCartIdAndProductId(cartEntity.getId(), productInCartRequest.getProductId());
        if (id != null) {
            if (productInCartRequest.getQuantity() == 0) {
                cartRefProductService.delete(id);
                return getDetailCartFromCartEntity(cartEntity);
            } else {
                CartRefProductInput cartRefProductInput = new CartRefProductInput(cartEntity.getId(), productInCartRequest.getProductId(), productInCartRequest.getQuantity());
                cartRefProductService.update(id, cartRefProductInput);
            }
            return getDetailCartFromCartEntity(cartEntity);
        }
        CartRefProductInput cartRefProductInput = new CartRefProductInput(cartEntity.getId(), productInCartRequest.getProductId(), productInCartRequest.getQuantity());
        cartRefProductService.create(cartRefProductInput);
        return getDetailCartFromCartEntity(cartEntity);
    }

    @Override
    public void deleteCart(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartEntity cartEntity = repository.getAll("WHERE user_id = " + userId + " ORDER BY id DESC LIMIT 1").get(0);
        cartRefProductService.deleteByCartId(cartEntity.getId());
    }

    @Override
    public CartDetailOutput createCart(Long userId) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        CartInput cartInput = new CartInput(userId);
        CartEntity cartEntity = repository.save(mapper.getEntityFromInput(cartInput));
        return getDetailCartFromCartEntity(cartEntity);
    }
}
