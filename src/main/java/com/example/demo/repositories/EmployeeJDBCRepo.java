package com.example.demo.repositories;

import com.example.demo.entities.Employee;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeJDBCRepo implements EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject("select count(*) from employee;", new MapSqlParameterSource(), Integer.class);
    }

    @Override
    public Employee findById(long id) {
        return (Employee) jdbcTemplate.queryForObject("select * from employee where id = :id;",
                new MapSqlParameterSource("id", id),
                new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employee;", new EmployeeMapper());
    }

    @Override
    public int create(Employee employee) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", employee.getName());
        parameterSource.addValue("salary", employee.getSalary());

        return jdbcTemplate.update("Insert Into employee (name, salary) values(:name, :salary);",
                parameterSource);
    }

    @Override
    public int update(Employee newEmployee, Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("name", newEmployee.getName());
        parameterSource.addValue("salary", newEmployee.getSalary());

        return jdbcTemplate.update("update employee set name = :name, salary = :salary where id = :id",
                parameterSource);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from employee where id = :id",
                new MapSqlParameterSource("id", id));
    }
}
