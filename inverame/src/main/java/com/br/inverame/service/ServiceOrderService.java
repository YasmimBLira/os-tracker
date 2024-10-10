package com.br.inverame.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.model.entity.dto.ServiceOrderDTO;
import com.br.inverame.model.mapper.ServiceOrderMapper;
import com.br.inverame.repository.ClientRepository;
import com.br.inverame.repository.EquipmentRepository;
import com.br.inverame.repository.ServiceOrderRepository;

import jakarta.transaction.Transactional;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private ServiceOrderMapper serviceOrderMapper;

    @Transactional
    public ServiceOrderDTO createServiceOrder(ServiceOrderDTO dto) {
        Client client = clientRepository.findByCodClient(dto.getCodClient())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente não encontrado com o código: " + dto.getCodClient()));

        // Verifica se o equipamento existe
        Equipment equipment = equipmentRepository.findBySerialNumber(dto.getEquipmentSerialNumber())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Equipamento não encontrado com o número de série: " + dto.getEquipmentSerialNumber()));

        // Mapeia o DTO para a entidade ServiceOrder
        ServiceOrder serviceOrder = serviceOrderMapper.mapToEntity(dto);

        // Define as referências do cliente e equipamento
        serviceOrder.setCodClient(client);
        serviceOrder.setEquipmentSerialNumber(equipment);

        // Define a data de registro com base na data do equipamento
        serviceOrder.setRegistrationDate(equipment.getRegistrationDate());

        // Verificar se já existe uma ordem de serviço com o mesmo osNumber
        if (serviceOrderRepository.existsByOsNumber(serviceOrder.getOsNumber())) {
            throw new IllegalArgumentException("O número da ordem de serviço já existe: " + serviceOrder.getOsNumber());
        }

        // Salva a ordem de serviço no repositório
        serviceOrder = serviceOrderRepository.save(serviceOrder);

        // Mapeia a entidade de volta para DTO e retorna
        return serviceOrderMapper.mapToDTO(serviceOrder);
    }

    public List<ServiceOrderDTO> findAll() {
        return serviceOrderRepository.findAll().stream()
                .map(serviceOrderMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ServiceOrderDTO> findByOsNumber(String osNumber) {
        return serviceOrderRepository.findByOsNumber(osNumber)
                .map(serviceOrderMapper::mapToDTO);
    }

    @Transactional
    public void deleteServiceOrderByOsNumber(String osNumber) {
        if (!serviceOrderRepository.existsByOsNumber(osNumber)) {
            throw new IllegalArgumentException("Service order not found with OS number: " + osNumber);
        }
        serviceOrderRepository.deleteByOsNumber(osNumber);
    }

    @Transactional
    public ServiceOrderDTO updateServiceOrder(String osNumber, ServiceOrderDTO dto) {
        // Verifica se a ordem de serviço existe
        ServiceOrder existingOrder = serviceOrderRepository.findByOsNumber(osNumber)
                .orElseThrow(() -> new IllegalArgumentException("Ordem de serviço não encontrada com número: " + osNumber));

        // Verifica se o cliente existe
        Client client = clientRepository.findByCodClient(dto.getCodClient())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cliente não encontrado com o código: " + dto.getCodClient()));

        // Verifica se o equipamento existe
        Equipment equipment = equipmentRepository.findBySerialNumber(dto.getEquipmentSerialNumber())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Equipamento não encontrado com o número de série: " + dto.getEquipmentSerialNumber()));

        // Atualiza os campos da ordem de serviço existente
        existingOrder.setResponsible(dto.getResponsible());
        existingOrder.setCodClient(client);
        existingOrder.setNfe(dto.getNfe());
        existingOrder.setEquipmentSerialNumber(equipment);
        existingOrder.setLocalization(dto.getLocalization());

        // Salva a ordem de serviço atualizada no repositório
        existingOrder = serviceOrderRepository.save(existingOrder);

        // Mapeia a entidade de volta para DTO e retorna
        return serviceOrderMapper.mapToDTO(existingOrder);
    }

}
