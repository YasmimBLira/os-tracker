package com.br.inverame.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "e-mail", nullable = false)
    private String email;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "Employee_Cod", nullable = false)
    private String employeeCod;

    //Getters and Setters
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

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmployeeCod() {
        return employeeCod;
    }
    public void setEmployeeCod(String employeeCod) {
        this.employeeCod = employeeCod;
    }

}


