package com.br.inverame.model.entity;


import java.time.LocalDateTime;

import com.br.inverame.model.enums.Choice;
import com.br.inverame.model.enums.Priority;

import jakarta.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "enterprise_name")
    private String enterprise_name;

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

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority; // Obrigatório

    @Column(name = "photo_url")
    private String photoURL; // URL ou caminho da foto

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice connectors; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice ihm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice carcass_damage; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice engine; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice engine_cables; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice fan; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Choice fan_carcass; 

    @Column(nullable = true)
    private String others; 

    // @Column(name = "qr_code")
    // private String qr_code;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getPhotoURL() {
        return photoURL;
    }
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public String getEquipmentName() {
        return equipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    public String getEnterprise_name() {
        return enterprise_name;
    }
    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }
    public Choice getConnectors() {
        return connectors;
    }
    public void setConnectors(Choice connectors) {
        this.connectors = connectors;
    }
    public Choice getIhm() {
        return ihm;
    }
    public void setIhm(Choice ihm) {
        this.ihm = ihm;
    }
    public Choice getCarcass_damage() {
        return carcass_damage;
    }
    public void setCarcass_damage(Choice carcass_damage) {
        this.carcass_damage = carcass_damage;
    }
    public Choice getEngine() {
        return engine;
    }
    public void setEngine(Choice engine) {
        this.engine = engine;
    }
    public Choice getEngine_cables() {
        return engine_cables;
    }
    public void setEngine_cables(Choice engine_cables) {
        this.engine_cables = engine_cables;
    }
    public Choice getFan() {
        return fan;
    }
    public void setFan(Choice fan) {
        this.fan = fan;
    }
    public Choice getFan_carcass() {
        return fan_carcass;
    }
    public void setFan_carcass(Choice fan_carcass) {
        this.fan_carcass = fan_carcass;
    }
    public String getOthers() {
        return others;
    }
    public void setOthers(String others) {
        this.others = others;
    }
    
    
}