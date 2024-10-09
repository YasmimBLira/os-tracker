package com.br.inverame.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.br.inverame.model.entity.dto.ClientUpdateDTO;
import com.br.inverame.service.ClientService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
public ResponseEntity<Map<String, Object>> createClient(@RequestBody Client client) {
    // Lógica para criar o cliente
    Map<String, Object> response = new HashMap<>();
    response.put("message", "Client created successfully");
    response.put("clientId", client.getId());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
}

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // exemplo: http://localhost:8080/api/clients/cnpj/80665555000100
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<?> getClientByCnpj(@PathVariable String cnpj) {
        Optional<Client> client = clientService.findByCnpj(cnpj);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    // exemplo: http://localhost:8080/api/clients/name/Empresa Y
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getClientByName(@PathVariable String name) {
        Optional<Client> client = clientService.findByName(name);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client with name not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody ClientUpdateDTO clientUpdateDTO) {
        try {
            Client updatedClient = clientService.updateClient(id, clientUpdateDTO);
            return new ResponseEntity<>("Client updated with ID: " + updatedClient.getId(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar cliente por CNPJ
    @PutMapping("/cnpj/{cnpj}")
    public ResponseEntity<String> updateClientByCnpj(@PathVariable String cnpj,
            @RequestBody ClientUpdateDTO clientUpdateDTO) {
        try {
            Client updatedClient = clientService.updateClientByCnpj(cnpj, clientUpdateDTO);
            return new ResponseEntity<>("Client updated with CNPJ: " + updatedClient.getCnpj(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return new ResponseEntity<>("Client deleted with ID: " + id, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

//  exemplo: http://localhost:8080/api/clients/cnpj/80665555000100
    @DeleteMapping("/cnpj/{cnpj}")
    public ResponseEntity<String> deleteClient(@PathVariable String cnpj) {
        try {
            clientService.deleteClientByCnpj(cnpj);
            return new ResponseEntity<>("Cliente com CNPJ " + cnpj + " foi deletado com sucesso.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
