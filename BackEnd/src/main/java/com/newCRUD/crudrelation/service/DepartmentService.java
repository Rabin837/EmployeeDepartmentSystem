package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.dto.DepartmentDTO;
import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;

import java.util.List;

public interface DepartmentService {
    public List<EmployeeDepartment> findAll();
    public DepartmentDTO findById(int theId);
    public void deleteById(int theId);
    public void save(DepartmentDTO theDepartment);

    public void update(DepartmentDTO departmentDTO,int id);

    Department findDepartmentById(int id);
}
