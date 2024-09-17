package com.br.inverame.service;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        if (employeeRepository.existsByName(employee.getName())) {
            throw new IllegalArgumentException("Funcionário com este nome já existe.");
        }
        if (employeeRepository.existsByEmployeeCod(employee.getEmployeeCod())) {
            throw new IllegalArgumentException("Funcionário com este código já existe.");
        }
        if (employeeRepository.existsByUserName(employee.getUserName())) {
            throw new IllegalArgumentException("Funcionário com este nome de usuário já existe.");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Funcionário com este e-mail já existe.");
        }

        // Definir a data de registro automaticamente na entidade Employee
        employee.setRegistrationDate(LocalDateTime.now());

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Funcionário com este ID não existe.");
        }
        if (employeeRepository.existsByName(employee.getName()) && !employeeRepository.findByName(employee.getName()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Funcionário com este nome já existe.");
        }
        if (employeeRepository.existsByEmployeeCod(employee.getEmployeeCod()) && !employeeRepository.findByEmployeeCod(employee.getEmployeeCod()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Funcionário com este código já existe.");
        }
        if (employeeRepository.existsByUserName(employee.getUserName()) && !employeeRepository.findByUserName(employee.getUserName()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Funcionário com este nome de usuário já existe.");
        }
        if (employeeRepository.existsByEmail(employee.getEmail()) && !employeeRepository.findByEmail(employee.getEmail()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Funcionário com este e-mail já existe.");
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Funcionário com este ID não existe.");
        }
    }
}
