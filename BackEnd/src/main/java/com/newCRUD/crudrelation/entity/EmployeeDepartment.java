package com.newCRUD.crudrelation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="EmployeeDepartment")
public class EmployeeDepartment{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="employee_department_id")
     private int id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Employee employee;
}
