package com.br.inverame.controller;

import java.util.List;
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

    @PostMapping("/create")
    public ResponseEntity<String> createClient(@RequestBody @Valid Client client) {
        try {
            Client savedClient = clientService.saveClient(client);
            return new ResponseEntity<>("Client created with ID: " + savedClient.getId(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // exemplo: http://localhost:8080/api/clients/cnpj?cnpj=80.665.555/0001-00
    @GetMapping("/cnpj")
    public ResponseEntity<?> getClientByCnpj(@RequestParam String cnpj) {
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
    @PutMapping("/cnpj")
    public ResponseEntity<String> updateClientByCnpj(@RequestParam String cnpj,
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

//  exemplo: http://localhost:8080/api/clients/cnpj?cnpj=80.665.555/0001-00
    @DeleteMapping("/cnpj")
    public ResponseEntity<String> deleteClient(@RequestParam String cnpj) {
        try {
            clientService.deleteClientByCnpj(cnpj);
            return new ResponseEntity<>("Cliente com CNPJ " + cnpj + " foi deletado com sucesso.", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
