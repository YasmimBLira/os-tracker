package com.br.inverame.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.inverame.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Buscar cliente pelo nome
    Optional<Client> findByName(String name);

    // Verificar se já existe cliente com esse nome
    boolean existsByName(String name);

    // Buscar cliente pelo CNPJ
    Optional<Client> findByCnpj(String cnpj);

    // Verificar se já existe cliente com esse CNPJ
    boolean existsByCnpj(String cnpj);

    // Buscar cliente pelo codClient
    Optional<Client> findByCodClient(String codClient);

    // Verificar se já existe cliente com esse codClient
    boolean existsByCodClient(String codClient);
}
