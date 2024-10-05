CREATE TABLE employees (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,  -- Unicidade para user_name
    email VARCHAR(255) NOT NULL,      -- Unicidade para email
    registration_date DATETIME(6) NOT NULL,
    employee_cod VARCHAR(50) NOT NULL, -- Unicidade para employee_cod
    password VARCHAR(255) NOT NULL,           -- Campo para armazenar a senha
    role ENUM('adm', 'normal') NOT NULL,      -- Campo para armazenar o papel (role)
    PRIMARY KEY (id, employee_cod),          -- Chave primária composta
     UNIQUE (user_name), 
     UNIQUE (email), 
     UNIQUE (employee_cod)
);
