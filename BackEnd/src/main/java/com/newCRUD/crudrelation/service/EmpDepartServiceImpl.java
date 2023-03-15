package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.Repo.EmployeeDepartmentRepo;
import com.newCRUD.crudrelation.dto.EmployeeDepartmentDTO;
import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.Employee;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpDepartServiceImpl implements EmpDepartService {
    EmployeeDepartmentRepo employeeDepartmentRepo;

    @Autowired
    public EmpDepartServiceImpl(EmployeeDepartmentRepo theEmployeeDepartmentRepo) {
        employeeDepartmentRepo = theEmployeeDepartmentRepo;
    }

    @Override
    public List<EmployeeDepartment> findAll() {
        return employeeDepartmentRepo.findAll();
    }

    @Override
    public EmployeeDepartmentDTO findById(int id) {
        Optional<EmployeeDepartment> empDepartOptional = employeeDepartmentRepo.findById(id);
        if (empDepartOptional.isEmpty()) {
            throw new RuntimeException("Did not find employee with id");
        } else {
            EmployeeDepartment employeeDepartment = empDepartOptional.get();
            EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
            employeeDepartmentDTO.setId(employeeDepartment.getId());
            return employeeDepartmentDTO;
        }
    }

    @Override
    public void deleteById(int theId) {
        employeeDepartmentRepo.deleteById(theId);
    }

    @Override
    public void save(Employee employee, List<Department> departmentList) {
        for (Department department : departmentList) {
            EmployeeDepartment employeeDepartment = new EmployeeDepartment();
            employeeDepartment.setDepartment(department);
            employeeDepartment.setEmployee(employee);
            employeeDepartmentRepo.save(employeeDepartment);
        }
    }

    @Override
    public List<Employee> findAllEmployeeByDepartment(Department department) {
        List<EmployeeDepartment> employeeDepartmentList = employeeDepartmentRepo.findAllByDepartment(department);
//        List<Employee> employeeList = employeeDepartmentList
//                .stream().map(EmployeeDepartment::getEmployee).collect(Collectors.toList());

        List<Employee> employeeList1 = new ArrayList<>();
        for (EmployeeDepartment employeeDepartment : employeeDepartmentList) {
            employeeList1.add(employeeDepartment.getEmployee());
        }
        return employeeList1;
    }

    @Override
    public List<Department> findAllDepartmentByEmployee(Employee employee) {
        List<EmployeeDepartment> employeeDepartmentList = employeeDepartmentRepo.findAllByEmployee(employee);
        List<Department> departmentList = new ArrayList<>();
        for (EmployeeDepartment employeeDepartment : employeeDepartmentList) {
            departmentList.add(employeeDepartment.getDepartment());
        }
        return departmentList;
    }
}