package com.springexersize.demo.repositry;

import com.springexersize.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EmployeeRepositry extends JpaRepository<Employee,Integer> {


    public Employee findByName(String name);

    public Employee findByCode(String code);
}
