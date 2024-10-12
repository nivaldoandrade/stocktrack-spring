ALTER TABLE product_warehouse ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE product_warehouse ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER product_update_updated_at
BEFORE UPDATE ON product_warehouse
FOR EACH ROW EXECUTE FUNCTION set_updated_at_trigger();