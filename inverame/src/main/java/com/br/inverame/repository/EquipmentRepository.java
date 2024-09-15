package com.br.inverame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.inverame.model.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsByName(String name);
    boolean existsBySerialNumber(String serialNumber);
}
