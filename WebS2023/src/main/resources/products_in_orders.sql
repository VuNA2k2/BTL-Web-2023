CREATE TABLE products_in_orders
(
    id               INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1),
    order_id         INTEGER NOT NULL,
    product_id       INTEGER,
    product_name     VARCHAR(255),
    product_price    DECIMAL(10, 2),
    product_quantity INTEGER NOT NULL DEFAULT 1,
    FOREIGN KEY (order_id) REFERENCES orders (id)
);