CREATE TABLE cart_ref_products
(
    id         INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    quantity   INTEGER,
    cart_id    INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    CONSTRAINT p_key PRIMARY KEY (id),
    CONSTRAINT ref_cart FOREIGN KEY (cart_id) REFERENCES public.carts (id),
    CONSTRAINT ref_product FOREIGN KEY (product_id) REFERENCES public.products (id)
)