package com.br.inverame.repository;



import com.br.inverame.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmployeeCod(String employeeCod);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeCod(String employeeCod);
    void deleteByEmail(String email);
    void deleteByEmployeeCod(String employeeCod);
}
