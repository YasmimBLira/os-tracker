CREATE TABLE equipments (
    id BIGINT GENERATED ALWAYS AS IDENTITY, -- Geração automática de IDs
    equipment_name VARCHAR(255) NOT NULL,
    serial_number VARCHAR(50) NOT NULL,
    carrier VARCHAR(255) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    enterprise_name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    model VARCHAR(255),
    current INT,
    power INT,
    voltage INT,
    registration_date TIMESTAMP(6) NOT NULL, -- Tipo de data corrigido
    priority VARCHAR(1) NOT NULL CHECK (priority IN ('A', 'B', 'C')), -- CHECK para simular ENUM
    photo_url VARCHAR(255),
    connectors VARCHAR(3) NOT NULL CHECK (connectors IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    ihm VARCHAR(3) NOT NULL CHECK (ihm IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    carcass_damage VARCHAR(3) NOT NULL CHECK (carcass_damage IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    engine VARCHAR(3) NOT NULL CHECK (engine IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    engine_cables VARCHAR(3) NOT NULL CHECK (engine_cables IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    fan VARCHAR(3) NOT NULL CHECK (fan IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    fan_carcass VARCHAR(3) NOT NULL CHECK (fan_carcass IN ('SIM', 'NAO')), -- CHECK para simular ENUM
    others VARCHAR(255),
    PRIMARY KEY (id),                  -- 'id' como chave primária
    UNIQUE (serial_number)              -- 'serial_number' deve ser único
);
