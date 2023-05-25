CREATE TABLE carts_ref_products
(
    id         INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1),
    cart_id    INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    quantity   INTEGER NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
)