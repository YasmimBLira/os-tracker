package com.br.inverame.model.entity.dto;

import java.time.LocalDateTime;

import com.br.inverame.model.enums.Localization;

public class ServiceOrderDTO {

    private String responsible; // Obrigatório
    private String osNumber; // Obrigatório
    private String nfe; // Opcional
    private String codClient; // ID do cliente
    private String equipmentSerialNumber; // Número de série do equipamento
    private Localization localization; // Enum

    // Getters e Setters

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

    public String getCodClient() {
        return codClient;
    }

    public void setCodClient(String codClient) {
        this.codClient = codClient;
    }

    public String getEquipmentSerialNumber() {
        return equipmentSerialNumber;
    }

    public void setEquipmentSerialNumber(String equipmentSerialNumber) {
        this.equipmentSerialNumber = equipmentSerialNumber;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }
}
