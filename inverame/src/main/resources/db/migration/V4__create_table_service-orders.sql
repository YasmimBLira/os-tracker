CREATE TABLE service_orders (
    id BIGINT NOT NULL AUTO_INCREMENT,
    responsible VARCHAR(100) NOT NULL,
    os_number VARCHAR(50) NOT NULL,
    nfe VARCHAR(100),
    registration_date DATETIME(6) NOT NULL,
    cod_client VARCHAR(10) NOT NULL,
    equipment_serial_number VARCHAR(50) NOT NULL,
    localization ENUM('ENTRADA', 'TRIAGEM', 'MANUTENCAO', 'FINALIZADO') NOT NULL,
    FOREIGN KEY (cod_client) REFERENCES clients(cod_client),
    FOREIGN KEY (equipment_serial_number) REFERENCES equipments(serial_number),
    PRIMARY KEY (id, os_number),
    UNIQUE (os_number)
);
