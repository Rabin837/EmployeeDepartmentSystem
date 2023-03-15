package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.dto.EmployeeDTO;
import com.newCRUD.crudrelation.entity.Employee;

import java.util.List;

public interface EmployeeService {

     public List<Employee> findAll();
     public EmployeeDTO findById(int theId);
     public void save(EmployeeDTO employeeDTO);
     public void update(EmployeeDTO employeeDTO,int id);
     public void deleteById(int theId);
}
