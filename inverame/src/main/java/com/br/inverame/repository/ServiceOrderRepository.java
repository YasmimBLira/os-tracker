package com.br.inverame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.inverame.model.entity.ServiceOrder;

public interface ServiceOrderRepository  extends JpaRepository<ServiceOrder, Long> {    

}
