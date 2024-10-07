package com.br.inverame.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeRequestDTO;
import com.br.inverame.model.entity.dto.EmployeeResponseDTO;
import com.br.inverame.model.mapper.EmployeeMapper;
import com.br.inverame.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
        // Verifica se o e-mail já existe
        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Funcionário com este e-mail já existe.");
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

    public List<EmployeeResponseDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeResponseDTO> findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .map(employeeMapper::mapToDTO);
    }

    public Optional<EmployeeResponseDTO> findByEmployeeCod(String employeeCod) {
        return employeeRepository.findByEmployeeCod(employeeCod)
                .map(employeeMapper::mapToDTO);
    }

    @Transactional
    public void deleteEmployeeByEmail(String email) {
        if (!employeeRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Employee not found with email: " + email);
        }
        employeeRepository.deleteByEmail(email);
    }

    @Transactional
    public void deleteEmployeeByEmployeeCod(String employeeCod) {
        if (!employeeRepository.existsByEmployeeCod(employeeCod)) {
            throw new IllegalArgumentException("Employee not found with employeeCod: " + employeeCod);
        }
        employeeRepository.deleteByEmployeeCod(employeeCod);
    }
}
