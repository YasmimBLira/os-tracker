package com.br.inverame.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.br.inverame.model.entity.Employee;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsById(Long id);
    boolean existsByName(String name);
    boolean existsByRegisterDate(LocalDateTime registerDate);
    boolean existsByEmployeeCod(String employeeCod);
    
    Optional<Employee> findById(Long id);
    Optional<Employee> findByName(String name);
    Optional<Employee> findByRegisterDate(LocalDateTime registerDate);
    Optional<Employee> findByEmployeeCod(String employeeCod);

}
