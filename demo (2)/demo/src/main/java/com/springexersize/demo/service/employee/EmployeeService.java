package com.springexersize.demo.service.employee;

import com.springexersize.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void addEmployee(Employee a);
    public Employee getEmployeeById(int id);

    public List<Employee> getAllEmployees();

    public void deleteEmployeeById(int id);

    public void updateEmployee(Employee employee, int id);

    public Employee getEmployeeByName(String name);

    public Employee getEmployeeByCode(String code);

    public void deleteAll();
}
