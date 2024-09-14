package com.br.inverame.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    public List<ServiceOrder> findAll(){
        return serviceOrderRepository.findAll();
    }

    public Optional<ServiceOrder> findById(Long id){
        return serviceOrderRepository.findById(id);
    }

    public ServiceOrder creatServiceOrder(ServiceOrder serviceOrder){
        return serviceOrderRepository.save(serviceOrder);
    }

    public void deleteById(Long id){
        serviceOrderRepository.deleteById(id);
    }
    
}
