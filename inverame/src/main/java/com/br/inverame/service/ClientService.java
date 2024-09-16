package com.br.inverame.service;

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
            throw new IllegalArgumentException("Cliente com este nome já existe.");
        }
        if (clientRepository.existsByCnpj(client.getCnpj())) {
            throw new IllegalArgumentException("Cliente com este CNPJ já existe.");
        }
        if (clientRepository.existsByCodClient(client.getCodClient())) {
            throw new IllegalArgumentException("Cliente com este código já existe.");
        }
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client updateClient(Long id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente com este ID não existe.");
        }
        if (clientRepository.existsByName(client.getName()) && !clientRepository.findByName(client.getName()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Cliente com este nome já existe.");
        }
        if (clientRepository.existsByCnpj(client.getCnpj()) && !clientRepository.findByCnpj(client.getCnpj()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Cliente com este CNPJ já existe.");
        }
        if (clientRepository.existsByCodClient(client.getCodClient()) && !clientRepository.findByCodClient(client.getCodClient()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Cliente com este código já existe.");
        }
        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Cliente com este ID não existe.");
        }
    }
}
