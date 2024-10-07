package com.br.inverame.model.mapper;



import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeRequestDTO;
import com.br.inverame.model.entity.dto.EmployeeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    // Mapeia EmployeeRequestDTO para Employee
    public Employee mapToEntity(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setPassword(employeeRequestDTO.getPassword());
        employee.setRole(employeeRequestDTO.getRole());
        employee.setEmployeeCod(employeeRequestDTO.getEmployeeCod());
        return employee;
    }

    // Mapeia Employee para EmployeeResponseDTO
    public EmployeeResponseDTO mapToDTO(Employee employee) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setName(employee.getName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setRole(employee.getRole());
        responseDTO.setRegistrationDate(employee.getRegistrationDate());
        responseDTO.setEmployeeCod(employee.getEmployeeCod());
        return responseDTO;
    }
}
