package com.br.inverame.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.dto.ClientUpdateDTO;
import com.br.inverame.service.ClientService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Criação de cliente com Map<String, Object>
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createClient(@RequestBody @Valid Client client) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client savedClient = clientService.saveClient(client);
            response.put("message", "Client created successfully");
            response.put("clientId", savedClient.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Retorna todos os clientes
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Buscar cliente por CNPJ
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getClientByCnpj(@PathVariable String cnpj) {
        Optional<Client> client = clientService.findByCnpj(cnpj);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar cliente por nome
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getClientByName(@PathVariable String name) {
        Optional<Client> client = clientService.findByName(name);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client with name not found", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar cliente por ID com Map<String, Object>
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateClient(@PathVariable Long id, @RequestBody ClientUpdateDTO clientUpdateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client updatedClient = clientService.updateClient(id, clientUpdateDTO);
            response.put("message", "Client updated successfully");
            response.put("clientId", updatedClient.getId());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Atualizar cliente por CNPJ com Map<String, Object>
    @PutMapping("/cnpj/{cnpj}")
    public ResponseEntity<Map<String, Object>> updateClientByCnpj(@PathVariable String cnpj, @RequestBody ClientUpdateDTO clientUpdateDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Client updatedClient = clientService.updateClientByCnpj(cnpj, clientUpdateDTO);
            response.put("message", "Client updated successfully");
            response.put("clientCnpj", updatedClient.getCnpj());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Deletar cliente por ID com Map<String, Object>
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.deleteById(id);
            response.put("message", "Client deleted successfully");
            response.put("clientId", id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Deletar cliente por CNPJ com Map<String, Object>
    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity<Map<String, Object>> deleteClient(@PathVariable String cnpj) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.deleteClientByCnpj(cnpj);
            response.put("message", "Client with CNPJ " + cnpj + " was successfully deleted.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (EntityNotFoundException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}