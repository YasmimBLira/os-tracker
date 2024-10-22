package com.br.inverame.repository;

import com.br.inverame.model.entity.Equipment;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    boolean existsBySerialNumber(String serialNumber);

    Optional<Equipment> findBySerialNumber(String serialNumber);

    @Transactional
    void deleteBySerialNumber(String serialNumber);
}
