package com.br.inverame.controller;

import com.br.inverame.model.entity.Employee;
import com.br.inverame.model.entity.dto.EmployeeResponseDTO;
import com.br.inverame.model.entity.dto.EmployeeRequestDTO;
import com.br.inverame.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Criar novo funcionário
    @PostMapping("/create")
    public ResponseEntity<String> createEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        try {
            EmployeeResponseDTO savedEmployee = employeeService.saveEmployee(employeeRequestDTO);
            return new ResponseEntity<>("Employee created with Code: " + savedEmployee.getEmployeeCod(),
                    HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Buscar todos os funcionários
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Buscar funcionário por email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String email) {
        Optional<EmployeeResponseDTO> employee = employeeService.findByEmail(email);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    // Buscar funcionário por employeeCod
    @GetMapping("/employeeCod/{employeeCod}")
    public ResponseEntity<?> getEmployeeByEmployeeCod(@PathVariable String employeeCod) {
        Optional<EmployeeResponseDTO> employee = employeeService.findByEmployeeCod(employeeCod);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar funcionário por email
    @PutMapping("/email/{email}")
    public ResponseEntity<String> updateEmployeeByEmail(@PathVariable String email,
            @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeByEmail(email, employeeRequestDTO);
            return new ResponseEntity<>("Employee updated with email: " + updatedEmployee.getEmail(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar funcionário por employeeCod
    @PutMapping("/employeeCod/{employeeCod}")
    public ResponseEntity<String> updateEmployeeByEmployeeCod(@PathVariable String employeeCod,
            @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        try {
            Employee updatedEmployee = employeeService.updateEmployeeByEmployeeCod(employeeCod, employeeRequestDTO);
            return new ResponseEntity<>("Employee updated with employeeCod: " + updatedEmployee.getEmployeeCod(),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Deletar funcionário por email
    @DeleteMapping("/email/{email}")
    public ResponseEntity<String> deleteEmployeeByEmail(@PathVariable String email) {
        try {
            employeeService.deleteEmployeeByEmail(email);
            return new ResponseEntity<>("Funcionário com email " + email + " foi deletado com sucesso.",
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Deletar funcionário por employeeCod
    @DeleteMapping("/employeeCod/{employeeCod}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeCod) {
        try {
            employeeService.deleteEmployeeByEmployeeCod(employeeCod);
            return new ResponseEntity<>("Funcionário com Código " + employeeCod + " foi deletado com sucesso.",
                     HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
