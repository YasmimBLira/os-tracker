package com.br.inverame.service;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeRequestDTO;
import com.br.inverame.model.entity.dto.EmployeeResponseDTO;
import com.br.inverame.model.enums.Role;
import com.br.inverame.model.mapper.EmployeeMapper;
import com.br.inverame.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper; // Adicionando o mapper

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
        // Verifica se o e-mail já existe
        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Funcionário com este e-mail já existe.");
        }
        // Verifica se o nome de usuário já existe
        if (employeeRepository.existsByUserName(employeeRequestDTO.getUserName())) {
            throw new IllegalArgumentException("Funcionário com este nome de usuário já existe.");
        }
        // Verifica se o código do funcionário já existe
        if (employeeRepository.existsByEmployeeCod(employeeRequestDTO.getEmployeeCod())) {
            throw new IllegalArgumentException("Funcionário com este código já existe.");
        }

        // Mapeia o DTO para a entidade Employee
        Employee employee = employeeMapper.mapToEntity(employeeRequestDTO);

        // Define a data de registro automaticamente
        employee.setRegistrationDate(LocalDateTime.now());

        // Salva o funcionário no repositório
        employee = employeeRepository.save(employee);

        // Mapeia a entidade Employee para o DTO de resposta e retorna
        return employeeMapper.mapToDTO(employee);
    }

    // Buscar todos os funcionários e mapear para EmployeeResponseDTO
    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::mapToDTO) // Usando o mapper
                .collect(Collectors.toList());
    }

    // Buscar funcionário por email e mapear para EmployeeResponseDTO
    public Optional<EmployeeResponseDTO> findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeMapper::mapToDTO); // Usando o mapper
    }

    // Buscar funcionário por employeeCod e mapear para EmployeeResponseDTO
    public Optional<EmployeeResponseDTO> findByEmployeeCod(String employeeCod) {
        return employeeRepository.findByEmployeeCod(employeeCod)
                .map(employeeMapper::mapToDTO); // Usando o mapper
    }

    // Atualizar funcionário por email
    public Employee updateEmployeeByEmail(String email, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with email: " + email));

        employee.setName(employeeRequestDTO.getName());
        employee.setUserName(employeeRequestDTO.getUserName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setEmployeeCod(employeeRequestDTO.getEmployeeCod());
        employee.setRole(employeeRequestDTO.getRole());
        employee.setPassword(employeeRequestDTO.getPassword());

        return employeeRepository.save(employee);
    }

    // Atualizar funcionário por employeeCod
    public Employee updateEmployeeByEmployeeCod(String employeeCod, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findByEmployeeCod(employeeCod)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with employeeCod: " + employeeCod));

        employee.setName(employeeRequestDTO.getName());
        employee.setUserName(employeeRequestDTO.getUserName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setEmployeeCod(employeeRequestDTO.getEmployeeCod());
        employee.setRole(employeeRequestDTO.getRole());
        employee.setPassword(employeeRequestDTO.getPassword());

        return employeeRepository.save(employee);
    }

    // Deletar funcionário por email
    @Transactional
    public void deleteEmployeeByEmail(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee not found with email: " + email);
        }
        employeeRepository.deleteByEmail(email);
    }

    // Deletar funcionário por employeeCod
    @Transactional
    public void deleteEmployeeByEmployeeCod(String employeeCod) {
        if (!employeeRepository.existsByEmployeeCod(employeeCod)) {
            throw new IllegalArgumentException("Employee not found with employeeCod: " + employeeCod);
        }
        employeeRepository.deleteByEmployeeCod(employeeCod);
    }
}
