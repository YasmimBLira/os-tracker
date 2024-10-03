package com.br.inverame.service;

import java.time.LocalDateTime;
import com.br.inverame.model.entity.Client;
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
    //Cadastrar client
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
    // update by ID
    public Client updateClient(Long id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client com ID não existe. ");
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
                &&
                !clientRepository.findByCodClient(client.getCodClient()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }
        return clientRepository.save(client);
    }
    // update by CNPJ
    public Client updateClientByCnpj(String cnpj, Client client) {
        Client existingClient = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Client with CNPJ " + cnpj + " not found."));

        // Verifique se o nome já existe, mas exclua o cliente atual da verificação
        if (clientRepository.existsByName(client.getName())
                && !clientRepository.findByName(client.getName()).get().getId().equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this name already exists.");
        }

        // Verifique se o CNPJ já existe, mas exclua o cliente atual da verificação
        if (clientRepository.existsByCnpj(client.getCnpj())
                && !clientRepository.findByCnpj(client.getCnpj()).get().getId().equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this CNPJ already exists.");
        }

        // Verifique se o código do cliente já existe, mas exclua o cliente atual da
        if (clientRepository.existsByCodClient(client.getCodClient())
                && !clientRepository.findByCodClient(client.getCodClient()).get().getId()
                        .equals(existingClient.getId())) {
            throw new IllegalArgumentException("Client with this code already exists.");
        }
        

        // Atualize os dados do cliente existente
        // existingClient.setName(client.getName());
        // existingClient.setPhone(client.getPhone());
        // existingClient.setCodClient(client.getCodClient());
        // existingClient.setRegisterDate(LocalDateTime.now()); // ou mantenha a data original

        return clientRepository.save(existingClient);
    }
    // deletar por ID
    public void deleteById(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Client with this ID does not exist.");
        }
    }
    // Método para deletar um cliente pelo CNPJ
    public void deleteClientByCnpj(String cnpj) {
        Optional<Client> client = clientRepository.deleteByCnpj(cnpj);

        if (client.isPresent()) {
            clientRepository.delete(client.get());
        } else {
            throw new EntityNotFoundException("Cliente com CNPJ " + cnpj + " não encontrado.");
        }
    }
}
