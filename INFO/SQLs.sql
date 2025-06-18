CREATE TABLE IF NOT EXISTS customers(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name text,
    phone text,
    address text
);