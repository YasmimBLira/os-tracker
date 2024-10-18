package com.br.inverame.model.entity;

import java.time.LocalDateTime;

import com.br.inverame.model.enums.Localization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_orders")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column(name = "responsible", nullable = false)
    private String responsible; // Obrigatório

    @Column(name = "os_number", nullable = false, unique = true)
    private String osNumber; // Obrigatório

    @Column(name = "nfe", nullable = true)
    private String nfe; // Opcional

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate; // Atualizado para LocalDateTime

    @ManyToOne
    @JoinColumn(name = "cod_client", referencedColumnName = "cod_client")
    private Client codClient; // Atualizado para seguir convenção de nomenclatura

    @Enumerated(EnumType.STRING)
    @Column(name = "localization", nullable = false)
    private Localization localization;

    @ManyToOne
    @JoinColumn(name = "equipment_serial_number", referencedColumnName = "serial_number")
    private Equipment equipmentSerialNumber; // Atualizado para seguir convenção de nomenclatura

    public ServiceOrder() {
        // Construtor padrão
    }

    public ServiceOrder(long id, String responsible, String osNumber, String nfe, LocalDateTime registrationDate,
            Client codClient, Localization localization, Equipment equipmentSerialNumber) {
        this.id = id;
        this.responsible = responsible;
        this.osNumber = osNumber;
        this.nfe = nfe;
        this.registrationDate = registrationDate;
        this.codClient = codClient;
        this.localization = localization;
        this.equipmentSerialNumber = equipmentSerialNumber;
    }

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

    public String getOsNumber() {
        return osNumber;
    }

    public void setOsNumber(String osNumber) {
        this.osNumber = osNumber;
    }

    public String getNfe() {
        return nfe;
    }

    public void setNfe(String nfe) {
        this.nfe = nfe;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Client getCodClient() {
        return codClient;
    }

    public void setCodClient(Client codClient) {
        this.codClient = codClient;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public Equipment getEquipmentSerialNumber() {
        return equipmentSerialNumber;
    }

    public void setEquipmentSerialNumber(Equipment equipmentSerialNumber) {
        this.equipmentSerialNumber = equipmentSerialNumber;
    }

    
    
}
