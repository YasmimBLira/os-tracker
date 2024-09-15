package com.br.inverame.model.entity.dto;

import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import com.br.inverame.model.entity.Equipment;
import com.br.inverame.model.entity.dto.EquipmentDTO;




public class EquipmentDTO {
    private Long id;
    private int osNumber;
    private String name;
    private String serialNumber;
    private String brand;
    private String model;
    private int current;
    private int power;
    private int voltage;
    private String priorityLevel;
    private LocalDateTime registrationDate;
    private String lastOS;
    private String description;
    private String photoURL;
    private List<Long> equipmentIds; // Lista com IDs dos equipamentos

    // Construtor que recebe um Equipment
    public EquipmentDTO(Equipment equipment) {
        this.id = equipment.getId();
        this.osNumber = equipment.getOsNumber();
        this.name = equipment.getName();
        this.serialNumber = equipment.getSerialNumber();
        this.brand = equipment.getBrand();
        this.model = equipment.getModel();
        this.current = equipment.getCurrent();
        this.power = equipment.getPower();
        this.voltage = equipment.getVoltage();
        this.priorityLevel = equipment.getPriorityLevel();
        this.registrationDate = equipment.getRegistrationDate();
        this.lastOS = equipment.getLastOS();
        this.description = equipment.getDescription();
        this.photoURL = equipment.getPhotoURL();
        // Inicialize a lista de equipamentos aqui
        this.equipmentIds = equipment.getClient().getEquipments().stream()
                            .map(Equipment::getId)
                            .collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOsNumber() {
        return osNumber;
    }

    public void setOsNumber(int osNumber) {
        this.osNumber = osNumber;
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

    public List<Long> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(List<Long> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }

    // Getters e Setters
}


    