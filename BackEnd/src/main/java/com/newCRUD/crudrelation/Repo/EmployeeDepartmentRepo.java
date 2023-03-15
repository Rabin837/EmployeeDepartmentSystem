package com.newCRUD.crudrelation.Repo;

import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.Employee;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDepartmentRepo extends JpaRepository<EmployeeDepartment,Integer> {

    List<EmployeeDepartment> findAllByDepartment(Department department);

    List<EmployeeDepartment> findAllByEmployee(Employee employee);

}
