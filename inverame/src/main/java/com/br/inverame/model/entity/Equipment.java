package com.br.inverame.model.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "os_number")
    private int osNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "responsible_person")
    private String responsiblePerson;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "current")
    private int current;

    @Column(name = "power")
    private int power;

    @Column(name = "voltage")
    private int voltage;

    @Column(name = "priority_level")
    private String priorityLevel;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "last_os")
    private String lastOS;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoURL; // URL ou caminho da foto

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; // Relação com a entidade Client
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getVoltage() {
        return voltage;
    }
    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
    public String getPriorityLevel() {
        return priorityLevel;
    }
    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getLastOS() {
        return lastOS;
    }
    public void setLastOS(String lastOS) {
        this.lastOS = lastOS;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPhotoURL() {
        return photoURL;
    }
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    public int getOsNumber() {
        return osNumber;
    }
    public void setOsNumber(int osNumber) {
        this.osNumber = osNumber;
    }
    public String getResponsiblePerson() {
        return responsiblePerson;
    }
    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    
}