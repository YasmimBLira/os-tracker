package com.br.inverame.service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.repository.ServiceOrderRepository;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.repository.ClientRepository;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<ServiceOrder> findAll() {
        return serviceOrderRepository.findAll();
    }

    public Optional<ServiceOrder> findById(Long id) {
        return serviceOrderRepository.findById(id);
    }

    public ServiceOrder createServiceOrder(Long clientId, Long equipmentId, ServiceOrder serviceOrder) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment não encontrado"));

        serviceOrder.setClient(client);
        serviceOrder.setEquipment(equipment);

        // Define a data de registro automaticamente
        serviceOrder.setRegistrationDate(LocalDateTime.now());

        return serviceOrderRepository.save(serviceOrder);
    }

    public ServiceOrder updateServiceOrder(Long id, Long equipmentId, ServiceOrder serviceOrder) {
        // Check if the ServiceOrder exists
        Optional<ServiceOrder> existingServiceOrder = serviceOrderRepository.findById(id);

        if (existingServiceOrder.isPresent()) {
            // Retrieve the existing ServiceOrder
            ServiceOrder updatedServiceOrder = existingServiceOrder.get();

            // Update the fields
            updatedServiceOrder.setResponsible(serviceOrder.getResponsible());
            updatedServiceOrder.setOsNumber(serviceOrder.getOsNumber());
            updatedServiceOrder.setNf_e(serviceOrder.getNf_e());
            updatedServiceOrder.setEquipment(serviceOrder.getEquipment());
            updatedServiceOrder.setRegistrationDate(serviceOrder.getRegistrationDate());

            // Update the client if provided
            if (serviceOrder.getClient() != null) {
                Client client = clientRepository.findById(serviceOrder.getClient().getId())
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                updatedServiceOrder.setClient(client);
            }

            // Save the updated ServiceOrder
            return serviceOrderRepository.save(updatedServiceOrder);
        } else {
            throw new RuntimeException("ServiceOrder not found");
        }
    }

    public void deleteById(Long id) {
        serviceOrderRepository.deleteById(id);
    }
}
