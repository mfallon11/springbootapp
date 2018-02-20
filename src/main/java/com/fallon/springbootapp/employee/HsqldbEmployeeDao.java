package com.fallon.springbootapp.employee;

import com.fallon.springbootapp.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class HsqldbEmployeeDao implements EmployeeDao {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public HsqldbEmployeeDao() {}

    // For testing to get around the Junit AutoWired issue
    public HsqldbEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean add(Employee employee) {
        List<Employee> result = jdbcTemplate.query(
                "SELECT employee_number, first_name, last_name FROM employees WHERE employee_number = ?", new Object[] { 1 },
                (rs, rowNum) -> new Employee(rs.getLong("employee_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));

        if(result.size() > 0) {
            return true;
        }

        if(jdbcTemplate.update("INSERT INTO employees(employee_number, first_name, last_name) VALUES (?,?,?)",
                employee.getEmployeeNumber(), employee.getFirstName(), employee.getLastName()) == 1) {
            return true;
        }

        return false;
    }

    @Override
    public Optional<Employee> get(int employeeNumber) {
        Optional<Employee> employee = Optional.empty();

        List<Employee> result = jdbcTemplate.query(
                "SELECT employee_number, first_name, last_name FROM employees WHERE employee_number = ?", new Object[] { 1 },
                (rs, rowNum) -> new Employee(rs.getLong("employee_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));

        if(result != null && !result.isEmpty()) {
            employee = Optional.of(result.get(0));
        }

        return employee;
    }

    @Override
    public Optional<List<Employee>> getAll() {
        Optional<List<Employee>> employees = Optional.empty();

        List<Employee> result = jdbcTemplate.query(
                "SELECT employee_number, first_name, last_name FROM employees",
                (rs, rowNum) -> new Employee(rs.getLong("employee_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));

        if(result != null) {
            return Optional.of(result);
        }

        return employees;
    }

    @Override
    public boolean delete(int employeeNumber) {
        if(jdbcTemplate.update("DELETE FROM employees WHERE employee_number=?", employeeNumber) == 1) {
            return true;
        }

        return false;
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getLong("employee_number"), rs.getString("first_name"), rs.getString("last_name"));
        }
    }
}


