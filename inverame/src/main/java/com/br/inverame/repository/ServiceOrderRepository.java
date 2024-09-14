package com.br.inverame.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.inverame.model.entity.ServiceOrder;
import com.br.inverame.model.enuns.Priority;

public interface ServiceOrderRepository  extends JpaRepository<ServiceOrder, Long> {    

    List<ServiceOrder> findByResponsible(String responsible);

    List<ServiceOrder> findByPriority(Priority priority);

    @Query("SELECT so FROM ServiceOrder so WHERE so.registrationDate > :date")
    List<ServiceOrder> findOrdersAfterDate(@Param("date") Date date);
}
