CREATE TABLE service_orders (
    id BIGINT GENERATED ALWAYS AS IDENTITY, -- Geração automática de IDs
    responsible VARCHAR(100) NOT NULL,
    os_number VARCHAR(50) NOT NULL,
    nfe VARCHAR(100),
    registration_date TIMESTAMP(6) NOT NULL, -- Corrige o tipo de data
    cod_client VARCHAR(10) NOT NULL,
    equipment_serial_number VARCHAR(50) NOT NULL,
    localization VARCHAR(12) NOT NULL CHECK (localization IN ('ENTRADA', 'TRIAGEM', 'MANUTENCAO', 'FINALIZADO')), -- Usa CHECK para restringir valores
    FOREIGN KEY (cod_client) REFERENCES clients(cod_client),
    FOREIGN KEY (equipment_serial_number) REFERENCES equipments(serial_number),
    PRIMARY KEY (id),                      -- Apenas 'id' como chave primária
    UNIQUE (os_number)                     -- 'os_number' deve ser único
);
