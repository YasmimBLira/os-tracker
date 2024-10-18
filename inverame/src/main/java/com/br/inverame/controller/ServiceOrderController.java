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

import com.br.inverame.model.entity.dto.ServiceOrderDTO;
import com.br.inverame.service.ServiceOrderService;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    // Criar nova ordem de serviço
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createServiceOrder(@RequestBody @Valid ServiceOrderDTO serviceOrderDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            ServiceOrderDTO savedServiceOrder = serviceOrderService.createServiceOrder(serviceOrderDTO);
            response.put("message", "Ordem de serviço criada com sucesso!");
            response.put("osNumber", savedServiceOrder.getOsNumber());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Buscar todas as ordens de serviço
    @GetMapping("/all")
    public ResponseEntity<List<ServiceOrderDTO>> getAllServiceOrders() {
        List<ServiceOrderDTO> serviceOrders = serviceOrderService.findAll();
        return new ResponseEntity<>(serviceOrders, HttpStatus.OK);
    }

    // Buscar ordem de serviço por osNumber
    @GetMapping("/os/{osNumber}")
    public ResponseEntity<?> getServiceOrderByOsNumber(@PathVariable String osNumber) {
        Optional<ServiceOrderDTO> serviceOrder = serviceOrderService.findByOsNumber(osNumber);
        if (serviceOrder.isPresent()) {
            return new ResponseEntity<>(serviceOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ordem de serviço não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar ordem de serviço por osNumber
    @PutMapping("/os/{osNumber}")
    public ResponseEntity<Map<String, Object>> updateServiceOrder(@PathVariable String osNumber,
            @RequestBody @Valid ServiceOrderDTO serviceOrderDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            ServiceOrderDTO updatedServiceOrder = serviceOrderService.updateServiceOrder(osNumber, serviceOrderDTO);
            response.put("message", "Ordem de serviço atualizada com sucesso!");
            response.put("osNumber", updatedServiceOrder.getOsNumber());
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Deletar ordem de serviço por osNumber
    @DeleteMapping("/os/{osNumber}")
    public ResponseEntity<Map<String, Object>> deleteServiceOrder(@PathVariable String osNumber) {
        Map<String, Object> response = new HashMap<>();
        try {
            serviceOrderService.deleteServiceOrderByOsNumber(osNumber);
            response.put("message", "Ordem de serviço com número: " + osNumber + " deletada com sucesso.");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
