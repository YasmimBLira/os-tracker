package com.br.inverame.repository;

import com.br.inverame.model.entity.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

    boolean existsByOsNumber(String osNumber);
    Optional<ServiceOrder> findByOsNumber(String osNumber);
    void deleteByOsNumber(String osNumber);
}
