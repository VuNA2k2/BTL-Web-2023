CREATE TABLE cart_ref_product (
    cid INTEGER REFERENCES carts (id),
    pid INTEGER,
    pname VARCHAR(255),
    quantity INTEGER,
    price DOUBLE PRECISION
);
