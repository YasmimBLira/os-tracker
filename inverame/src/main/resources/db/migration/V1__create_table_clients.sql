-- Flyway migration script for the 'client' table


CREATE TABLE clients (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cnpj VARCHAR(20) NOT NULL,
  cod_client VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(25) ,
  register_date DATETIME(6) NOT NULL,
  PRIMARY KEY (id, cnpj, cod_client),
  UNIQUE (cnpj),             -- CNPJ deve ser único
  UNIQUE (cod_client),       -- Código do cliente deve ser único
  UNIQUE (name)              -- Nome do cliente deve ser único     
);

