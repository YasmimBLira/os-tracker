package com.br.inverame.repository;

import com.br.inverame.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByName(String name);
    boolean existsByCnpj(String cnpj);
    boolean existsByCodClient(String codClient);

    Optional<Client> findByName(String name);
    Optional<Client> findByCnpj(String cnpj);
    Optional<Client> findByCodClient(String codClient);
    Optional<Client> findById(Client client);
}
