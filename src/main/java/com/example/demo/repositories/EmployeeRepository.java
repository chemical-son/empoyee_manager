package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    Integer count();

    Employee findById(long id);

    List<Employee> findAll();

    int create(Employee employee);

    int update(Employee newEmployee, Long id);

    void delete(Long id);
}
