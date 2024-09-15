package com.br.inverame.controller;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.dto.ClientDTO;
import com.br.inverame.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody @Valid Client client) {
        try {
            Client savedClient = clientService.saveClient(client);
            return new ResponseEntity<>("Client created with ID: " + savedClient.getId(), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        List<ClientDTO> clientDTOs = clients.stream()
            .map(ClientDTO::new)
            .collect(Collectors.toList());
        return new ResponseEntity<>(clientDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            ClientDTO clientDTO = new ClientDTO(client.get());
            return new ResponseEntity<>(clientDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateClient(@PathVariable Long id, @RequestBody @Valid Client client) {
        try {
            Client updatedClient = clientService.updateClient(id, client);
            return new ResponseEntity<>("Client updated with ID: " + updatedClient.getId(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return new ResponseEntity<>("Client deleted with ID: " + id, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
