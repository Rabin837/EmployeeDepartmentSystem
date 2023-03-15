package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.dto.EmployeeDepartmentDTO;
import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.Employee;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;

import java.util.List;

public interface EmpDepartService {
    public List<EmployeeDepartment> findAll();
    public EmployeeDepartmentDTO findById(int theId);
    public void deleteById(int theId);

    List<Employee> findAllEmployeeByDepartment(Department department);

    List<Department> findAllDepartmentByEmployee(Employee employee);
    public void save(Employee employeeList, List<Department> departmentList);
}
