CREATE TABLE cart_ref_product (
    cart_id INTEGER REFERENCES carts (id),
    product_id INTEGER,
    product_name VARCHAR(255),
    quantity INTEGER,
    price DOUBLE PRECISION
);
