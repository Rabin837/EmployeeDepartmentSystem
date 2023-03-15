package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.Repo.DepartmentRepository;
import com.newCRUD.crudrelation.dto.DepartmentDTO;
import com.newCRUD.crudrelation.dto.EmployeeDTO;
import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.Employee;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private EmpDepartService empDepartService;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository thedepartmentRepository, EmpDepartService empDepartService, @Lazy EmployeeService employeeService) {
        departmentRepository = thedepartmentRepository;
        this.empDepartService = empDepartService;
        this.employeeService = employeeService;
    }

    @Override
    public List<EmployeeDepartment> findAll() {
        // return departmentRepository.findAll()
        return empDepartService.findAll();

    }

    @Override
    public DepartmentDTO findById(int theId) {
        Optional<Department> departmentOptional = departmentRepository.findById(theId);
        List<Employee> employeeList = empDepartService.findAllEmployeeByDepartment(departmentOptional.get());

        DepartmentDTO departmentDTO = new DepartmentDTO();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee employee : employeeList) {
            employeeDTOList.add(EmployeeDTO.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .build());
        }
        departmentDTO.setDepartment_id(departmentOptional.get().getId());
        departmentDTO.setDepartment_Name(departmentOptional.get().getDepartment_Name());
        departmentDTO.setEmployees(employeeDTOList);

        return departmentDTO;
//
//        if (departmentOptional.isEmpty()) {
//            throw new RuntimeException("Did not find any Department with above id.");
//        }else {
//            Department department = departmentOptional.get();
//            DepartmentDTO departmentDTO = new DepartmentDTO();
//            departmentDTO.setDepartment_id(department.getId());
//            departmentDTO.setDepartment_Name(department.getDepartment_Name());
//            return departmentDTO;
//        }
    }

    @Override
    public void deleteById(int theId) {
        departmentRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public void save(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setDepartment_Name(departmentDTO.getDepartment_Name());
        departmentRepository.save(department);
    }

    @Override
    public void update(DepartmentDTO departmentDTO, int id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) {
            throw new RuntimeException("Did not find any Department with above id.");
        } else {
            Department department = departmentOptional.get();
            department.setDepartment_Name(departmentDTO.getDepartment_Name());
            departmentRepository.save(department);
        }
    }

    @Override
    public Department findDepartmentById(int id) {
        return departmentRepository.findById(id)
                .orElse(null);
    }
}
