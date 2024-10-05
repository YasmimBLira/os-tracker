package com.br.inverame.service;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeUpdateDTO;
import com.br.inverame.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Salvar novo funcionário
    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Funcionário com este e-mail já existe.");
        }
        if (employeeRepository.existsByUserName(employee.getUserName())) {
            throw new IllegalArgumentException("Funcionário com este nome de usuário já existe.");
        }
        if (employeeRepository.existsByEmployeeCod(employee.getEmployeeCod())) {
            throw new IllegalArgumentException("Funcionário com este código já existe.");
        }
        return employeeRepository.save(employee);
    }

    // Buscar todos os funcionários
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Buscar funcionário por email
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Buscar funcionário por employeeCod
    public Optional<Employee> findByEmployeeCod(String employeeCod) {
        return employeeRepository.findByEmployeeCod(employeeCod);
    }

    // Atualizar funcionário por email
    public Employee updateEmployeeByEmail(String email, EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with email: " + email));

        employee.setName(employeeUpdateDTO.getName());
        employee.setUserName(employeeUpdateDTO.getUserName());
        employee.setEmail(employeeUpdateDTO.getEmail());
        employee.setEmployeeCod(employeeUpdateDTO.getEmployeeCod());

        return employeeRepository.save(employee);
    }

    // Atualizar funcionário por employeeCod
    public Employee updateEmployeeByEmployeeCod(String employeeCod, EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employee = employeeRepository.findByEmployeeCod(employeeCod)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with employeeCod: " + employeeCod));

        employee.setName(employeeUpdateDTO.getName());
        employee.setUserName(employeeUpdateDTO.getUserName());
        employee.setEmail(employeeUpdateDTO.getEmail());
        employee.setEmployeeCod(employeeUpdateDTO.getEmployeeCod());

        return employeeRepository.save(employee);
    }

    // Deletar funcionário por email
    public void deleteEmployeeByEmail(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee not found with email: " + email);
        }
        employeeRepository.deleteByEmail(email);
    }

    // Deletar funcionário por employeeCod
    public void deleteEmployeeByEmployeeCod(String employeeCod) {
        if (!employeeRepository.existsByEmployeeCod(employeeCod)) {
            throw new IllegalArgumentException("Employee not found with employeeCod: " + employeeCod);
        }
        employeeRepository.deleteByEmployeeCod(employeeCod);
    }
}
