package com.br.inverame.model.mapper;

import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.model.entity.dto.ServiceOrderDTO;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderMapper {

    public ServiceOrder mapToEntity(ServiceOrderDTO dto) {
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setResponsible(dto.getResponsible());
        serviceOrder.setOsNumber(dto.getOsNumber());
        serviceOrder.setNfe(dto.getNfe());
        serviceOrder.setLocalization(dto.getLocalization());
        return serviceOrder;
    }

    public ServiceOrderDTO mapToDTO(ServiceOrder entity) {
        ServiceOrderDTO dto = new ServiceOrderDTO();
        dto.setResponsible(entity.getResponsible());
        dto.setOsNumber(entity.getOsNumber());
        dto.setNfe(entity.getNfe());
        dto.setCodClient(entity.getCodClient().getCodClient());
        dto.setEquipmentSerialNumber(entity.getEquipmentSerialNumber().getSerialNumber());
        dto.setLocalization(entity.getLocalization());
        return dto;
    }
}
