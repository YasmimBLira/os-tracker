package com.br.inverame.model.entity.dto;

import com.br.inverame.model.enums.Choice;
import com.br.inverame.model.enums.Priority;

import jakarta.validation.constraints.NotNull;

public class EquipmentUpdateDTO {

    @NotNull
    private String equipmentName;

    @NotNull
    private String serialNumber;
    
    @NotNull
    private String carrier;

    @NotNull
    private String receiver;

    @NotNull
    private String enterprise_name;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private int current;

    @NotNull
    private int power;

    @NotNull
    private int voltage;

    @NotNull
    private Priority priority;

    @NotNull
    private Choice connectors;

    @NotNull
    private Choice ihm;

    @NotNull
    private Choice carcass_damage;

    @NotNull
    private Choice engine;

    @NotNull
    private Choice engine_cables;

    @NotNull
    private Choice fan;

    @NotNull
    private Choice fan_carcass;

    private String others;

    // Getters and Setters

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    
}