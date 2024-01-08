package com.springexersize.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="department")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "code")
    @NotNull(message = "department code is required")

    private String code;

    @NotNull(message = "department name is required")

    @Column(name = "name")
    private String name;

    @NotNull(message = "department description is required")

    @Column(name = "description")

    private String description;

}
