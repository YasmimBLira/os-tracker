-- Flyway migration script for the 'client' table


CREATE TABLE clients (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cnpj VARCHAR(255) NOT NULL,
  cod_client VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(255) ,
  register_date DATETIME(6) NOT NULL,
  PRIMARY KEY (id, cnpj, cod_client),
  UNIQUE (cnpj),             -- CNPJ deve ser único
  UNIQUE (cod_client),       -- Código do cliente deve ser único
  UNIQUE (name)              -- Nome do cliente deve ser único     
);

