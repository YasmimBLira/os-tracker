package com.br.inverame.model.entity.dto;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.enums.Role;

public class EmployeeResponseDTO {
    private String name;
    private String userName;
    private String employeeCod;
    private String email;
    private Role role;


    public EmployeeResponseDTO() {
    }

    // Construtor que recebe um Employee
    public EmployeeResponseDTO(Employee employee) {
        this.name = employee.getName();
        this.userName = employee.getUserName();
        this.employeeCod = employee.getEmployeeCod();
        this.email = employee.getEmail();
        this.role = employee.getRole();
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
    public Role getRole() { return role; } 
    public void setRole(Role role) { this.role = role; }
}
