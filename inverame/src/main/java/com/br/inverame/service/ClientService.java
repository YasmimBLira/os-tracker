package com.br.inverame.service;

import java.time.LocalDateTime;
import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.dto.ClientUpdateDTO;
import com.br.inverame.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    // buscar por ID
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    // buscar por CNPJ
    public Optional<Client> findByCnpj(String cnpj) {
        return clientRepository.findByCnpj(cnpj);
    }

    // buscar por name
    public Optional<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    // buscar todos
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // ============================ATUALIZAR================================================
    // Atualizar cliente por ID
    public Client updateClient(Long id, ClientUpdateDTO clientUpdateDTO) {
        // Verificamos se o cliente existe e obtemos o cliente existente.
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client com ID não existe."));

        // Verificações adicionais
        if (clientRepository.existsByName(clientUpdateDTO.getName())
                && !clientRepository.findByName(clientUpdateDTO.getName()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }
        if (clientRepository.existsByCnpj(clientUpdateDTO.getCnpj())
                && !clientRepository.findByCnpj(clientUpdateDTO.getCnpj()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }
        if (clientRepository.existsByCodClient(clientUpdateDTO.getCodClient())
                && !clientRepository.findByCodClient(clientUpdateDTO.getCodClient()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }

        // Atualizar apenas os campos permitidos
        existingClient.setName(clientUpdateDTO.getName());
        existingClient.setPhone(clientUpdateDTO.getPhone());
        existingClient.setCodClient(clientUpdateDTO.getCodClient());
        existingClient.setCnpj(clientUpdateDTO.getCnpj());

        return clientRepository.save(existingClient);
    }

    // Atualizar cliente por CNPJ
    public Client updateClientByCnpj(String cnpj, ClientUpdateDTO clientUpdateDTO) {
        Client existingClient = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new IllegalArgumentException("Client com CNPJ não existe."));

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
        if (clientRepository.existsByCnpj(clientUpdateDTO.getCnpj())
                && !clientRepository.findByCnpj(clientUpdateDTO.getCnpj()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }

        // Atualizar apenas os campos permitidos
        existingClient.setName(clientUpdateDTO.getName());
        existingClient.setPhone(clientUpdateDTO.getPhone());
        existingClient.setCodClient(clientUpdateDTO.getCodClient());
        existingClient.setCnpj(clientUpdateDTO.getCnpj());

        return clientRepository.save(existingClient);
    }

    // =============================DELETAR===========================================
    // deletar por ID
    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Client with this ID does not exist.");
        }
    }

    // Deletar um cliente pelo CNPJ
    public void deleteClientByCnpj(String cnpj) {
        Client client = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado."));

        clientRepository.delete(client);
    }

}
