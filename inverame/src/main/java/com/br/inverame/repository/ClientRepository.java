package com.br.inverame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.inverame.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAll();
    
}
