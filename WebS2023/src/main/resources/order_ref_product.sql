CREATE TABLE order_ref_product (
    order_id INTEGER REFERENCES orders(id),
    product_id INTEGER ,
    product_name VARCHAR(255);,
    quantity INTEGER,
    price DECIMAL(10, 2)
);
