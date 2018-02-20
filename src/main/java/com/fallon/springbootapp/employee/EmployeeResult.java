package com.fallon.springbootapp.employee;

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
