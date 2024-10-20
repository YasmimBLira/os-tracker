-- Tabela de employees corrigida para PostgreSQL
CREATE TABLE employees (
    id BIGINT GENERATED ALWAYS AS IDENTITY, -- Geração automática de IDs
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,           -- Unicidade para email
    registration_date TIMESTAMP(6) NOT NULL, -- Corrige o tipo de data/tempo
    employee_cod VARCHAR(50) NOT NULL,     -- Unicidade para employee_cod
    password VARCHAR(255) NOT NULL,        -- Campo para armazenar a senha
    role VARCHAR(10) NOT NULL CHECK (role IN ('ADM', 'NORMAL')), -- Usa CHECK em vez de ENUM
    PRIMARY KEY (id),                      -- Apenas o 'id' como chave primária
    UNIQUE (email),                        -- Garante que o email seja único
    UNIQUE (employee_cod)                  -- Garante que o código do empregado seja único
);
