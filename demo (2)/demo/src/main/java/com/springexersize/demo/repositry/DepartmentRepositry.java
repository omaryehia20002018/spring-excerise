package com.springexersize.demo.repositry;

import com.springexersize.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepositry extends JpaRepository<Department,Integer> {
     public Department findByName(String name);


     public Department findByDescription(String description);

     public Department findByCode(String code);

}
