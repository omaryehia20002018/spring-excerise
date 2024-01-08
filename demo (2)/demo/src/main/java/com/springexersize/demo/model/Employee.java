package com.springexersize.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.springexersize.demo.validation.employee.UniqueCode;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code",unique = true)
    @NotNull(message = "employee code is required")

    @UniqueCode(message = "code already exists")
    private String code;


    @Column(name = "name")
    @NotNull(message = "employee name is required")

    private String name;

    @Column(name = "birthofdate")
    @NotNull(message = "employee birthdate is required")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthofdate;

    @Column(name = "address")
    @NotNull(message = "employee's address  is required")

    private String address;

    @Column(name = "mobile")
    @NotNull(message = "employee's mobile is required")


    private String mobile;

    @Column(name = "salary")
    @NotNull(message = "employee's salary is required")

    private Double salary;


    @ManyToOne
    @JoinColumn(name = "departmentid")
    @JsonIgnore

    @Valid
    private Department department;


}
