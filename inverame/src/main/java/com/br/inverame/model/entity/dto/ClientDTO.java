package com.br.inverame.model.entity.dto;

public class ClientDTO {

    private String name;
    private String phone;
    private String cnpj;

    // Construtor
    public ClientDTO() {}

    public ClientDTO(String name, String phone, String cnpj) {
        this.name = name;
        this.phone = phone;
        this.cnpj = cnpj;
    }

    // Getters and Setters
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
