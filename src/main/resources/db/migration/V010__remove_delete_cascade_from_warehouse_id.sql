ALTER TABLE product_warehouse
	DROP CONSTRAINT product_warehouse_warehouse_id_fkey,
	ADD CONSTRAINT product_warehouse_warehouse_id_fkey
	FOREIGN KEY(warehouse_id) REFERENCES warehouse(id);