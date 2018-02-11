package com.fallon.springbootapp.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private HsqldbEmployeeDao employeeDao;

    public EmployeeController() {}

    // For testing to get around the Junit AutoWired issue
    public EmployeeController(HsqldbEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @RequestMapping(value="/employee/add", method=RequestMethod.POST)
    public BooleanResult addEmployee(@RequestBody Employee employee) {
        return new BooleanResult(employeeDao.add(employee));
    }

    // Using the json as input here instead of just passing an int to be consistent
    @RequestMapping(value="/employee/get", method=RequestMethod.POST)
    public EmployeeResult getEmployee(@RequestBody EmployeeNumber employeeNumber) {
        Optional<Employee> employee = employeeDao.get(employeeNumber.getEmployeeNumber());
        if(employee.isPresent()) {
            return new EmployeeResult(true, new Employee[]{employee.get()});
        }

        return new EmployeeResult(false, new Employee[0]);
    }

    @RequestMapping(value="/employee/getall")
    public EmployeeResult getAllEmployees() {
        Optional<List<Employee>> employees = employeeDao.getAll();
        if(employees.isPresent()) {
            List<Employee> list = employees.get();
            Employee[] employeeArray = new Employee[list.size()];
            return new EmployeeResult(true, list.toArray(employeeArray));
        }

        return new EmployeeResult(false, new Employee[0]);
    }

    // Using the json as input here instead of just passing an int to be consistent
    @RequestMapping(value="/employee/delete", method=RequestMethod.POST)
    public BooleanResult deleteEmployee(@RequestBody EmployeeNumber employeeNumber) {
        return new BooleanResult(employeeDao.delete(employeeNumber.getEmployeeNumber()));
    }
}

class EmployeeNumber {
    private int employeeNumber;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}

class BooleanResult {
    private boolean success;

    public BooleanResult(boolean success) {
        this.success = success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}

class EmployeeResult {
    private boolean success;
    private Employee[] employees;

    public EmployeeResult(boolean success, Employee[] employees) {
        this.success = success;
        this.employees = employees;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
