CREATE TABLE orders
(
    id         INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1),
    user_id    INTEGER   NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
)

alter table orders
    add status VARCHAR default 'PENDING' not null;