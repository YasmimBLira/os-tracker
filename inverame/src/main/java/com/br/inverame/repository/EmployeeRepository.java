package com.br.inverame.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.inverame.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByRegistrationDate(LocalDateTime registrationDate);
    boolean existsByEmployeeCod(String employeeCod);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    
    Optional<Employee> findById(Long id);
    Optional<Employee> findByName(String name);
    Optional<Employee> findByRegistrationDate(LocalDateTime registrationDate);
    Optional<Employee> findByEmployeeCod(String employeeCod);
    Optional<Employee> findByUserName(String userName);
    Optional<Employee> findByEmail(String email);
    

}
