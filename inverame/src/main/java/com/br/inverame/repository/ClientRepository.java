package com.br.inverame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.inverame.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
