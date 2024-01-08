package com.springexersize.demo.service.department;

import com.springexersize.demo.model.Department;

import java.util.List;

public interface DepartmentService {
    public void addDepartment(Department a);
    public Department getDepartmentById(int id);

    public List<Department> getAllDepartments();

    public void deleteDepartmentById(int id);

    public void updateDepartment(Department department, int id);

    public Department getDepartmentByName(String name);

    public Department getDepartmentByCode(String code);

    public Department getDepartmentByDescription(String description);

    public void deleteAll();
}
