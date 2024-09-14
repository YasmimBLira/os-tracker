package com.br.inverame.controller;

import java.util.*;


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
    public ResponseEntity<ServiceOrder> createServiceOrder(@RequestBody ServiceOrder serviceOrder) {
        ServiceOrder createdServiceOrder = serviceOrderService.creatServiceOrder(serviceOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> updateServiceOrder(@PathVariable Long id, @RequestBody ServiceOrder serviceOrder) {
        // Find the existing ServiceOrder by ID
        Optional<ServiceOrder> existingServiceOrder = serviceOrderService.findById(id);
        if (existingServiceOrder.isPresent()) {
            ServiceOrder updatedServiceOrder = existingServiceOrder.get();
            // Update the existing service order with the new values
            updatedServiceOrder.setResponsible(serviceOrder.getResponsible());
            updatedServiceOrder.setNumbersOs(serviceOrder.getNumbersOs());
            updatedServiceOrder.setNf_e(serviceOrder.getNf_e());
            updatedServiceOrder.setPriority(serviceOrder.getPriority());
            updatedServiceOrder.setRegistrationDate(serviceOrder.getRegistrationDate());
            // Save the updated service order
            ServiceOrder savedServiceOrder = serviceOrderService.creatServiceOrder(updatedServiceOrder);
            return ResponseEntity.ok(savedServiceOrder);
        } else {
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
