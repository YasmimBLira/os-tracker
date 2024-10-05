CREATE TABLE employees (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL UNIQUE,  -- Unicidade para user_name
    email VARCHAR(255) NOT NULL UNIQUE,      -- Unicidade para email
    registration_date DATETIME NOT NULL,
    employee_cod VARCHAR(50) NOT NULL UNIQUE, -- Unicidade para employee_cod
    PRIMARY KEY (id, employee_cod)  -- Chave primária composta
);
