package com.br.inverame.model.entity;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Data;

import com.br.inverame.model.enuns.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class ServiceOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long equipmentId;  //autoincrement

    @Column(nullable = false)
    private String responsible; // pbrigatoro

    @Column(nullable = false, unique = true)
    private String numbersOs;  //obrigatorio

    @Column(nullable = false)
    private String nf_e;        //opcional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;  //obrigatorio

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Data registrationDate; //obrigatiro "devr se feito de forma automatica"

   
    public ServiceOrder() {
    }

    

    public ServiceOrder(long equipmentId, String responsible, String numbersOs, String nf_e, Priority priority,
            Data registrationDate) {
        this.equipmentId = equipmentId;
        this.responsible = responsible;
        this.numbersOs = numbersOs;
        this.nf_e = nf_e;
        this.priority = priority;
        this.registrationDate = registrationDate;
    }



    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getNumbersOs() {
        return numbersOs;
    }

    public void setNumbersOs(String numbersOs) {
        this.numbersOs = numbersOs;
    }

    public String getNf_e() {
        return nf_e;
    }

    public void setNf_e(String nf_e) {
        this.nf_e = nf_e;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Data getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Data registrationDate) {
        this.registrationDate = registrationDate;
    }

    

}
