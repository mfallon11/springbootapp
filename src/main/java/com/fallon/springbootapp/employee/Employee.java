package com.fallon.springbootapp.employee;

public class Employee {
    private long employeeNumber;
    private String firstName, lastName;

    public Employee() {

    }

    public Employee(long employeeNumber, String firstName, String lastName) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[employeeNumber=%d, firstName='%s', lastName='%s']",
                employeeNumber, firstName, lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getEmployeeNumber() {

        return employeeNumber;
    }

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
