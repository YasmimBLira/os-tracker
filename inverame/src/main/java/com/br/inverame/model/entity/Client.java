package com.br.inverame.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "cod_client", nullable = false)
    private String codClient;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    public Client() {
    }

    public Client(Long id, String name, String phone, LocalDateTime registerDate, String codClient, String cnpj) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.registerDate = registerDate;
        this.codClient = codClient;
        this.cnpj = cnpj;
    }
    // Getters and Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public LocalDateTime getRegisterDate() {return registerDate;}
    public void setRegisterDate(LocalDateTime registerDate) {this.registerDate = registerDate;}

    public String getCodClient() {return codClient;}
    public void setCodClient(String codClient) {this.codClient = codClient;}

    public String getCnpj() {return cnpj;}
    public void setCnpj(String cnpj) {this.cnpj = cnpj;}


}
