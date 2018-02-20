package com.fallon.springbootapp.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value="/employee/get")
    public EmployeeResult getEmployee(@RequestParam(value="employeeNumber", defaultValue="1") int employeeNumber) {
        Optional<Employee> employee = employeeDao.get(employeeNumber);
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
    @RequestMapping(value="/employee/delete", method=RequestMethod.DELETE)
    public BooleanResult deleteEmployee(@RequestBody EmployeeNumber employeeNumber) {
        return new BooleanResult(employeeDao.delete(employeeNumber.getEmployeeNumber()));
    }
}


