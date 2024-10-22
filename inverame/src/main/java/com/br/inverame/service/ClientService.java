package com.br.inverame.service;

import java.time.LocalDateTime;
import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.dto.ClientUpdateDTO;
import com.br.inverame.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // =============================CRIAR============================
    // Cadastrar client
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

    // =========================BUSCAR===============================
    // Buscar por codClient
    public Optional<Client> findByCodClient(String codClient) {
        return clientRepository.findByCodClient(codClient);
    }

    // Buscar por name
    public Optional<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    // Buscar por CNPJ
    public Optional<Client> findByCnpj(String cnpj) {
        return clientRepository.findByCnpj(cnpj);
    }

    // Buscar todos os clientes
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // ============================ATUALIZAR================================================
    // Atualizar cliente por CNPJ
    public Client updateClientByCnpj(String cnpj, ClientUpdateDTO clientUpdateDTO) {
        Client existingClient = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new IllegalArgumentException("Client with CNPJ not found."));

        // Verificações adicionais
        if (clientRepository.existsByName(clientUpdateDTO.getName())
                && !clientRepository.findByName(clientUpdateDTO.getName()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
        if (clientRepository.existsByCodClient(clientUpdateDTO.getCodClient())
                && !clientRepository.findByCodClient(clientUpdateDTO.getCodClient()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }

        // Atualizar campos permitidos
        existingClient.setName(clientUpdateDTO.getName());
        existingClient.setPhone(clientUpdateDTO.getPhone());
        existingClient.setCodClient(clientUpdateDTO.getCodClient());
        existingClient.setCnpj(clientUpdateDTO.getCnpj());

        return clientRepository.save(existingClient);
    }

    // Atualizar cliente por codClient
    public Client updateClientByCodClient(String codClient, ClientUpdateDTO clientUpdateDTO) {
        Client existingClient = clientRepository.findByCodClient(codClient)
                .orElseThrow(() -> new IllegalArgumentException("Client with codClient not found."));

        // Verificações adicionais
        if (clientRepository.existsByName(clientUpdateDTO.getName())
                && !clientRepository.findByName(clientUpdateDTO.getName()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
        if (clientRepository.existsByCnpj(clientUpdateDTO.getCnpj())
                && !clientRepository.findByCnpj(clientUpdateDTO.getCnpj()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }

        // Atualizar campos permitidos
        existingClient.setName(clientUpdateDTO.getName());
        existingClient.setPhone(clientUpdateDTO.getPhone());
        existingClient.setCodClient(clientUpdateDTO.getCodClient());
        existingClient.setCnpj(clientUpdateDTO.getCnpj());

        return clientRepository.save(existingClient);
    }

    // =============================DELETAR===========================================
    // Deletar cliente por CNPJ
    public void deleteClientByCnpj(String cnpj) {
        Client client = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Client with CNPJ " + cnpj + " not found."));
        clientRepository.delete(client);
    }

    // Deletar cliente por codClient
    public void deleteClientByCodClient(String codClient) {
        Client client = clientRepository.findByCodClient(codClient)
                .orElseThrow(() -> new EntityNotFoundException("Client with codClient " + codClient + " not found."));
        clientRepository.delete(client);
    }
}
