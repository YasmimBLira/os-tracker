package com.br.inverame.model.entity;

import java.time.LocalDateTime;

import com.br.inverame.model.enuns.Priority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_order")
public class ServiceOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  // Renomeado para id para maior clareza

    @Column(nullable = false)
    private String responsible; // Obrigatório

    @Column(nullable = false, unique = true)
    private String numbersOs;  // Obrigatório

    @Column(nullable = true)
    private String nf_e;        // Opcional

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;  // Obrigatório

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate; // Obrigatório, deve ser feito de forma automática

    public ServiceOrder() {
        // Construtor padrão
    }

    public ServiceOrder(String responsible, String numbersOs, String nf_e, Priority priority,
                        LocalDateTime registrationDate) {
        this.responsible = responsible;
        this.numbersOs = numbersOs;
        this.nf_e = nf_e;
        this.priority = priority;
        this.registrationDate = registrationDate;
    }

    // Getters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
