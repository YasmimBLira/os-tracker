package com.br.inverame.model.entity.dto;

import jakarta.validation.constraints.NotBlank;

public class ClientUpdateDTO {

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Phone cannot be blank.")
    private String phone;

    @NotBlank(message = "Client code cannot be blank.")
    private String codClient;

    @NotBlank(message = "CNPJ cannot be blank.")
    private String cnpj;

    // Getters e Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCodClient() { return codClient; }
    public void setCodClient(String codClient) { this.codClient = codClient; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}
