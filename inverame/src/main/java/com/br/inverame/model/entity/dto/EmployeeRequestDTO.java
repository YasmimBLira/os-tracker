package com.br.inverame.model.entity.dto;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.enums.Role;

import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "UserName cannot be null.")
    private String userName;

    @NotNull(message = "EmployeeCode cannot be null.")
    private String employeeCod;

    @NotNull(message = "Email cannot be null.")
    private String email;

    @NotNull(message = "O campo de função é obrigatório.")
    private Role role;

    @NotNull(message = "Password cannot be null.")
    private String password;

    public EmployeeRequestDTO() {
    }

    // Constructor that receives an Employee
    public EmployeeRequestDTO(Employee employee) {
        this.name = employee.getName();
        this.userName = employee.getUserName();
        this.employeeCod = employee.getEmployeeCod();
        this.email = employee.getEmail();
        this.role = employee.getRole();
        this.password = employee.getPassword();
    }

    // Compact Getters and Setters
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

    public String getEmployeeCod() {
        return employeeCod;
    }

    public void setEmployeeCod(String employeeCod) {
        this.employeeCod = employeeCod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
