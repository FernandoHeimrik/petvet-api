CREATE TABLE user(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	profile VARCHAR(20) NOT NULL,
	crmv_number VARCHAR(20),
	crmv_uf VARCHAR(2),
	enabled BOOLEAN NOT NULL,
	creation_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	avatar VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;