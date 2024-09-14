package com.br.inverame.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.inverame.model.entity.Client;
import com.br.inverame.repository.ClientRepository;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id){
        return clientRepository.findById(id);
    }

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteById(Long id){
        clientRepository.deleteById(id);
    }

    public Client updateClient(Client client) {
        if (client.getId() == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }

        // Check if the client exists
        Optional<Client> existingClientOpt = clientRepository.findById(client.getId());
        if (!existingClientOpt.isPresent()) {
            throw new IllegalArgumentException("Client with ID " + client.getId() + " does not exist");
        }

        // Save the updated client
        return clientRepository.save(client);
    }

}
