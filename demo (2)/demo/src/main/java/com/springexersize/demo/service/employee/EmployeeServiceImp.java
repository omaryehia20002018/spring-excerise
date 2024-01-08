package com.springexersize.demo.service.employee;

import com.springexersize.demo.model.Department;
import com.springexersize.demo.model.Employee;
import com.springexersize.demo.repositry.EmployeeRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepositry erepo;

    @Autowired

    public EmployeeServiceImp(EmployeeRepositry erepo) {
        this.erepo = erepo;
    }

    @Override
    public void addEmployee(Employee a) {
        erepo.save(a);
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee e=erepo.findById(id).orElseThrow(()-> new NoSuchElementException("no employee with this id"));
        return e;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee>e=erepo.findAll();
        return e;
    }

    @Override
    public void deleteEmployeeById(int id) {
        getByIdOrThrowException(id);
        erepo.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee employee, int id) {
        getByIdOrThrowException(id);
         Employee e=erepo.findById(id).get();
        if(employee.getName()!=null){
            e.setName(employee.getName());
        }

        if(employee.getCode()!=null){
            e.setCode(employee.getCode());
        }

        if(employee.getAddress()!=null){
            e.setAddress(employee.getAddress());
        }

        if(employee.getBirthofdate()!=null){
            e.setBirthofdate(employee.getBirthofdate());
        }

        if(employee.getMobile()!=null){
            e.setMobile(employee.getMobile());
        }

        if(employee.getSalary()!=null){
            e.setSalary(employee.getSalary());
        }

        erepo.save(e);

    }

    @Override
    public Employee getEmployeeByName(String name) {
        return erepo.findByName(name);
    }

    @Override
    public Employee getEmployeeByCode(String code) {
        return erepo.findByCode(code);
    }


    @Override
    public void deleteAll() {
        erepo.deleteAll();
    }

    public Employee getByIdOrThrowException(int id){
        return erepo.findById(id).orElseThrow(()->new NoSuchElementException("no employee with this id"));
    }
}
