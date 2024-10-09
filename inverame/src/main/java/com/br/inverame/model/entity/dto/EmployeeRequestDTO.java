package com.br.inverame.model.entity.dto;

import com.br.inverame.model.enums.Role;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {

    @NotNull(message = "Name cannot be null.")
    private String name;

    @NotNull(message = "Email cannot be null.")
    private String email;

    @NotNull(message = "Password cannot be null.")
    private String password;

    @NotNull(message = "Role cannot be null.")
    private Role role;

    @NotNull(message = "EmployeeCode cannot be null.")
    private String employeeCod;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmployeeCod() {
        return employeeCod;
    }

    public void setEmployeeCod(String employeeCod) {
        this.employeeCod = employeeCod;
    }
}
