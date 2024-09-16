package com.br.inverame.model.entity.dto;

import java.time.LocalDateTime;
import com.br.inverame.model.entity.Employee;

public class EmployeeDTO {
    private Long id;
    private String name;
    private LocalDateTime registerDate;
    private String employeeCod;
    private String userName;
    private String email;

    // Construtor que recebe um Employee
    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.registerDate = employee.getRegisterDate();
        this.employeeCod = employee.getEmployeeCod();
        this.userName = employee.getUserName();
        this.email = employee.getEmail();
    }

    // Getters e Setters compactos
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDateTime getRegisterDate() { return registerDate; }
    public void setRegisterDate(LocalDateTime registerDate) { this.registerDate = registerDate; }
    public String getEmployeeCod() { return employeeCod; }
    public void setEmployeeCod(String employeeCod) { this.employeeCod = employeeCod; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
