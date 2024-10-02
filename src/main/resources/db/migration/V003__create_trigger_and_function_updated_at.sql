CREATE OR REPLACE FUNCTION set_updated_at_trigger() RETURNS TRIGGER
AS $$
	BEGIN
		NEW.updated_at = CURRENT_TIMESTAMP;
		RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER category_update_updated_at
BEFORE UPDATE ON category
FOR EACH ROW EXECUTE FUNCTION set_updated_at_trigger();