

CREATE TABLE IF NOT EXISTS users(
	id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
	full_name VARCHAR NOT NULL,
	username VARCHAR UNIQUE NOT NULL,
	password VARCHAR NOT NULL
);