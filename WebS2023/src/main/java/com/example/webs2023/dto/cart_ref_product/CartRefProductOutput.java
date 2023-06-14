package com.example.webs2023.dto.cart_ref_product;
import com.example.webs2023.dto.product.ProductOutput;

public class CartRefProductOutput {
    private Long id;
    private Long cartId;
    private Long productId;
    private Long quantity;
    private ProductOutput product;

    public CartRefProductOutput() {
    }

    public CartRefProductOutput(Long id, Long cartId, Long productId, Long quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartRefProductOutput(Long id, Long cartId, Long productId, Long quantity, ProductOutput product) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public ProductOutput getProduct() {
        return product;
    }

    public void setProduct(ProductOutput product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
