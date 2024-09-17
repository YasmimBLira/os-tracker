package com.br.inverame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.inverame.model.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsByEquipmentName(String equipmentName);
    boolean existsBySerialNumber(String serialNumber);
}
