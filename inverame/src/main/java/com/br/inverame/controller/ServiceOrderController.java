package com.br.inverame.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.service.ServiceOrderService;

@RestController
@RequestMapping("/api/serviceOrders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderService;

    @GetMapping
    public ResponseEntity<List<ServiceOrder>> getAllServiceOrders() {
        List<ServiceOrder> serviceOrders = serviceOrderService.findAll();
        return ResponseEntity.ok(serviceOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrder> getServiceOrderById(@PathVariable Long id) {
        Optional<ServiceOrder> serviceOrder = serviceOrderService.findById(id);
        if (serviceOrder.isPresent()) {
            return ResponseEntity.ok(serviceOrder.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ServiceOrder> createServiceOrder(@RequestBody Map<String, Object> request) {
        try {
            // Extrai o clientId e equipmentId do corpo da requisição
            Long clientId = Long.valueOf(request.get("clientId").toString());
            Long equipmentId = Long.valueOf(request.get("equipmentId").toString());

            // Cria a nova ServiceOrder
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setResponsible(request.get("responsible").toString());
            serviceOrder.setOsNumber(request.get("osNumber").toString());
            serviceOrder.setNf_e(request.get("nf_e") != null ? request.get("nf_e").toString() : null);
            serviceOrder.setRegistrationDate(LocalDateTime.now());

            // Cria a ServiceOrder com base no clientId e equipmentId
            ServiceOrder savedServiceOrder = serviceOrderService.createServiceOrder(clientId, equipmentId, serviceOrder);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedServiceOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> updateServiceOrder(@PathVariable Long id,
            @RequestBody Map<String, Object> request) {
        try {
            // Extrai o equipmentId do corpo da requisição
            Long equipmentId = Long.valueOf(request.get("equipmentId").toString());

            // Cria um objeto ServiceOrder para atualizar
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setResponsible(request.get("responsible").toString());
            serviceOrder.setOsNumber(request.get("osNumber").toString());
            serviceOrder.setNf_e(request.get("nf_e") != null ? request.get("nf_e").toString() : null);
            serviceOrder.setRegistrationDate(LocalDateTime.now());

            // Atualiza a ServiceOrder
            ServiceOrder updatedServiceOrder = serviceOrderService.updateServiceOrder(id, equipmentId, serviceOrder);
            return ResponseEntity.ok(updatedServiceOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceOrder(@PathVariable Long id) {
        Optional<ServiceOrder> serviceOrder = serviceOrderService.findById(id);
        if (serviceOrder.isPresent()) {
            serviceOrderService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
