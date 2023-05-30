CREATE TABLE rates
(
    id                  INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1),
    comment             VARCHAR(255) NOT NULL,
    user_id             INTEGER      NOT NULL,
    star                INTEGER      NOT NULL,
    created_at          TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    product_in_order_id INTEGER      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_in_order_id) REFERENCES products_in_orders (id)
)

alter table rates
drop constraint rates_product_in_order_id_fkey;

alter table rates
    add constraint rates_product_id_fkey
        foreign key (product_in_order_id) references products;