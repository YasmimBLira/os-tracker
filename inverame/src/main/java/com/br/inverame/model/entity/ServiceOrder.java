package com.br.inverame.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_order")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Renomeado para id para maior clareza

    @Column(nullable = false)
    private String responsible; // Obrigatório

    @Column(nullable = false, unique = true)
    private String osNumber; // Obrigatório

    @Column(nullable = true)
    private String nf_e; // Opcional

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate; // Obrigatório, deve ser feito de forma automática (retirar)

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "equipment_id") // Nome da coluna que será adicionada na tabela service_order
    private Equipment equipment;

    public ServiceOrder() {
        // Construtor padrão
    }

    public ServiceOrder(long id, String responsible, String osNumber, String nf_e, LocalDateTime registrationDate,
            Client client, Equipment equipment) {
        this.id = id;
        this.responsible = responsible;
        this.osNumber = osNumber;
        this.nf_e = nf_e;
        this.registrationDate = registrationDate;
        this.client = client;
        this.equipment = equipment;
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
    
    public String getNf_e() {
        return nf_e;
    }

    public void setNf_e(String nf_e) {
        this.nf_e = nf_e;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getOsNumber() {
        return osNumber;
    }

    public void setOsNumber(String osNumber) {
        this.osNumber = osNumber;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
    
}
