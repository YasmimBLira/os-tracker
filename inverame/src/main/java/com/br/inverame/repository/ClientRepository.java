package com.br.inverame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.inverame.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByName(String name);
    boolean existsByCnpj(String cnpj);
    boolean existsByCodClient(String codClient);

    Optional<Client> findByName(String name);
    Optional<Client> deleteByCnpj(String cnpj);
    Optional<Client> findByCodClient(String codClient);
    Optional<Client> findByCnpj(String cnpj);
}
