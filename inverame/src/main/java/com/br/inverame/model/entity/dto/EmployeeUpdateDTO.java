package com.br.inverame.model.entity.dto;

import com.br.inverame.model.entity.Employee;

public class EmployeeUpdateDTO {
    private String name;
    private String userName;
    private String employeeCod;
    private String email;
    
    // Construtor que recebe um Employee
    public EmployeeUpdateDTO(Employee employee) {
        this.name = employee.getName();
        this.userName = employee.getUserName();
        this.employeeCod = employee.getEmployeeCod();
        this.email = employee.getEmail();
    }

    // Getters e Setters compactos
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getEmployeeCod() { return employeeCod; }
    public void setEmployeeCod(String employeeCod) { this.employeeCod = employeeCod; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
