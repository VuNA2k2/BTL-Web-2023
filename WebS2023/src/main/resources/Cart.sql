CREATE TABLE carts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES user (id),
    total_money DOUBLE PRECISION
);
