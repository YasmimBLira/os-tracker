package com.br.inverame.model.mapper;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeRequestDTO;
import com.br.inverame.model.entity.dto.EmployeeResponseDTO;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    // Método para mapear Employee para EmployeeResponseDTO
    public EmployeeResponseDTO mapToDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setName(employee.getName());
        dto.setUserName(employee.getUserName());
        dto.setEmail(employee.getEmail());
        dto.setRole(employee.getRole());
        dto.setEmployeeCod(employee.getEmployeeCod());
        return dto;
    }

    // Método para mapear de EmployeeRequestDTO para Employee (usado na criação e atualização)
    public Employee mapToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setUserName(employeeRequestDTO.getUserName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setEmployeeCod(employeeRequestDTO.getEmployeeCod());
        employee.setRole(employeeRequestDTO.getRole());
        employee.setPassword(employeeRequestDTO.getPassword());
        return employee;
    }
}
