package com.br.inverame.model.entity.dto;

import com.br.inverame.model.entity.Client;
import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private String registerDate;
    private String codClient;
    private String cnpj;
    private List<Long> equipmentIds;

    // Construtor que recebe um Client
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.phone = client.getPhone();
        this.registerDate = client.getRegisterDate().toString();
        this.codClient = client.getCodClient();
        this.cnpj = client.getCnpj();
        this.equipmentIds = client.getEquipments().stream()
                                .map(equipment -> equipment.getId())
                                .collect(Collectors.toList());
    }

    // Getters e Setters
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getCodClient() {
        return codClient;
    }

    public void setCodClient(String codClient) {
        this.codClient = codClient;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Long> getEquipmentIds() {
        return equipmentIds;
    }

    public void setEquipmentIds(List<Long> equipmentIds) {
        this.equipmentIds = equipmentIds;
    }
}
