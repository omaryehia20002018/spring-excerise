package com.springexersize.demo.service.department;

import com.springexersize.demo.model.Department;
import com.springexersize.demo.repositry.DepartmentRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private DepartmentRepositry drepo;
    @Autowired

    public DepartmentServiceImp(DepartmentRepositry drepo) {
        this.drepo = drepo;

    }

    @Override
    public void addDepartment(Department a) {

        drepo.save(a);
    }

    @Override
    public Department getDepartmentById(int id) {
        Department d=drepo.findById(id).orElseThrow(()->new NoSuchElementException("no department with this id"));


        return d;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department>d=drepo.findAll();
        return d;
    }

    @Override
    public void deleteDepartmentById(int id) {
         getByIdOrThrowException(id);
         drepo.deleteById(id);
    }

    @Override
    public void updateDepartment(Department department, int id) {
        getByIdOrThrowException(id);
       Department d=drepo.findById(id).get();

       if(department.getName()!=null){
           d.setName(department.getName());
       }

        if(department.getCode()!=null){
            d.setCode(department.getCode());
        }

        if(department.getDescription()!=null){
            d.setDescription(department.getDescription());
        }
        drepo.save(d);
    }
    @Override
    public Department getDepartmentByName(String name){
      return   drepo.findByName(name);
    }

    @Override
    public Department getDepartmentByCode(String code) {
        return drepo.findByCode(code);
    }

    @Override
    public Department getDepartmentByDescription(String description) {
        return drepo.findByDescription(description);
    }

    @Override
    public void deleteAll() {
        drepo.deleteAll();
    }

    public Department getByIdOrThrowException(int id){
        return drepo.findById(id).orElseThrow(()->new NoSuchElementException("no department with this id"));
    }
}
