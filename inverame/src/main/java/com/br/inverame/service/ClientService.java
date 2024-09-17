package com.br.inverame.service;

import java.time.LocalDateTime;
import com.br.inverame.model.entity.Client;
import com.br.inverame.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        if (clientRepository.existsByName(client.getName())) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
        if (clientRepository.existsByCnpj(client.getCnpj())) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }
        if (clientRepository.existsByCodClient(client.getCodClient())) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }

        // Define a data de registro automaticamente
        client.setRegisterDate(LocalDateTime.now());

        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client updateClient(Long id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client with this ID does not exist.");
        }
        if (clientRepository.existsByName(client.getName())
                && !clientRepository.findByName(client.getName()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
        if (clientRepository.existsByCnpj(client.getCnpj())
                && !clientRepository.findByCnpj(client.getCnpj()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }
        if (clientRepository.existsByCodClient(client.getCodClient())
                && !clientRepository.findByCodClient(client.getCodClient()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }
        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Client with this ID does not exist.");
        }
    }
}
