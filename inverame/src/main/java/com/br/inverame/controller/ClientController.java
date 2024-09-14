package com.br.inverame.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.inverame.model.entity.Client;
import com.br.inverame.service.ClientService;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Optional<Client> client = clientService.findById(id);
        return client.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        try {
            Client savedClient = clientService.createClient(client);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

     @PutMapping("/{id}") // Adicionar este endpoint
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> existingClient = clientService.findById(id);
        
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setName(client.getName());
            updatedClient.setPhone(client.getPhone());
            updatedClient.setRegisterDate(client.getRegisterDate());
            
            // Presumindo que o método updateClient no ClientService realiza a atualização
            Client savedClient = clientService.updateClient(updatedClient);
            return ResponseEntity.ok(savedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
