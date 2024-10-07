CREATE TABLE IF NOT EXISTS product(
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR UNIQUE NOT NULL,
    code VARCHAR UNIQUE NOT NULL,
    brand VARCHAR NOT NULL,
    category_id UUID NOT NULL,

    FOREIGN KEY(category_id) REFERENCES category(id)
);