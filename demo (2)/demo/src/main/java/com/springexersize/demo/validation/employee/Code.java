package com.springexersize.demo.validation.employee;

import com.springexersize.demo.repositry.EmployeeRepositry;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Code implements ConstraintValidator<UniqueCode,String> {

    private EmployeeRepositry es;


    @Autowired
    public Code(EmployeeRepositry es) {
        this.es = es;
    }

    public Code(){

    }

    @Override
    public void initialize(UniqueCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    //@Bean
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
       if(s==null){
           return true;
       }
       if(this.es==null){
           return true;
       }

       return es.findByCode(s)==null;
    }


}
