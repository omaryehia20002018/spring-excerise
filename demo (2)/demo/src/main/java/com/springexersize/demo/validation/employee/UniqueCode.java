package com.springexersize.demo.validation.employee;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =Code.class)
public @interface UniqueCode {
    public String message() default "already exists";

    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
