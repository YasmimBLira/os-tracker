package com.br.inverame.model.entity.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.br.inverame.model.entity.Client;
import com.br.inverame.model.entity.Equipment;

public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private LocalDateTime registerDate;
    private String codClient;
    private String cnpj;
    private List<Integer> osNumbers; // Lista com números de OS dos equipamentos

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.phone = client.getPhone();
        this.registerDate = client.getRegisterDate();
        this.codClient = client.getCodClient();
        this.cnpj = client.getCnpj();
        this.osNumbers = client.getEquipments().stream()
                            .map(Equipment::getOsNumber)
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

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
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

    public List<Integer> getOsNumbers() {
        return osNumbers;
    }

    public void setOsNumbers(List<Integer> osNumbers) {
        this.osNumbers = osNumbers;
    }
}
