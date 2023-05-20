CREATE TABLE public.orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    total_money DECIMAL(10, 2),
    status VARCHAR(255)
);

