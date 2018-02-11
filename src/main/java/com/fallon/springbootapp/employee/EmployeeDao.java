package com.fallon.springbootapp.employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    public boolean add(Employee employee);
    public Optional<Employee> get(int employeeNumber);
    public Optional<List<Employee>> getAll();
    public boolean delete(int employeeNumber);
}
