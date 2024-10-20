CREATE TABLE clients (
  id BIGINT GENERATED ALWAYS AS IDENTITY, -- Geração automática de IDs
  cnpj VARCHAR(20) NOT NULL,
  cod_client VARCHAR(10) NOT NULL,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(25),
  register_date TIMESTAMP(6) NOT NULL, -- Corrige o tipo de data/tempo para PostgreSQL
  PRIMARY KEY (id),  -- 'id' como chave primária é suficiente
  UNIQUE (cnpj),             -- CNPJ deve ser único
  UNIQUE (cod_client),       -- Código do cliente deve ser único
  UNIQUE (name)              -- Nome do cliente deve ser único
);
