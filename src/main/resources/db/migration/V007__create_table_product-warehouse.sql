CREATE TABLE IF NOT EXISTS product_warehouse(
	product_id UUID NOT NULL,
	warehouse_id UUID NOT NULL,
	quantity INT NOT NULL,
	location VARCHAR NOT NULL,

	PRIMARY KEY(product_id, warehouse_id),
	FOREIGN KEY(product_id) REFERENCES product(id) ON DELETE CASCADE,
	FOREIGN KEY(warehouse_id) REFERENCES warehouse(id) ON DELETE CASCADE
);