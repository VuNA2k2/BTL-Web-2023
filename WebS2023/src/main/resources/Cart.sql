CREATE TABLE carts (
    id SERIAL PRIMARY KEY,
    cusid INTEGER REFERENCES customers (id),
    totalmoney DOUBLE PRECISION
);
