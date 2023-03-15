package com.newCRUD.crudrelation.service;

import com.newCRUD.crudrelation.Repo.EmployeeDepartmentRepo;
import com.newCRUD.crudrelation.Repo.EmployeeRepository;
import com.newCRUD.crudrelation.dto.DepartmentDTO;
import com.newCRUD.crudrelation.dto.EmployeeDTO;
import com.newCRUD.crudrelation.entity.Department;
import com.newCRUD.crudrelation.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmpDepartService empDepartService;

    private DepartmentService departmentService;
    private final EmployeeDepartmentRepo employeeDepartmentRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepo, EmpDepartService empDepartService, DepartmentService departmentService,
                               EmployeeDepartmentRepo employeeDepartmentRepo) {
        employeeRepository = theEmployeeRepo;
        this.empDepartService = empDepartService;
        this.departmentService = departmentService;
        this.employeeDepartmentRepo = employeeDepartmentRepo;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional
    public EmployeeDTO findById(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        List<Department> departmentList= empDepartService.findAllDepartmentByEmployee(employeeOptional.get());

        EmployeeDTO employeeDTO=new EmployeeDTO();
        List<DepartmentDTO> departmentDTOList=new ArrayList<>();
//        if (employeeOptional.isEmpty()) {
//            throw new RuntimeException("Did not find employee with id");
//        } else {
//            Employee employee = employeeOptional.get();
//            EmployeeDTO employeeDTO = new EmployeeDTO();
//            employeeDTO.setId(employee.getId());
//            employeeDTO.setEmail(employee.getEmail());
//            employeeDTO.setFirstName(employee.getFirstName());
//            employeeDTO.setLastName(employee.getLastName());
//            return employeeDTO;
//        }
        for (Department department :departmentList) {
            departmentDTOList.add(DepartmentDTO.builder().
                    department_id(department.getId()).
                    department_Name(department.getDepartment_Name())
                    .build());
        }
        employeeDTO.setId(employeeOptional.get().getId());
        employeeDTO.setFirstName(employeeOptional.get().getFirstName());
        employeeDTO.setLastName(employeeOptional.get().getLastName());
        employeeDTO.setEmail(employeeOptional.get().getEmail());
        employeeDTO.setAllDepartments(departmentDTOList);
        return employeeDTO;
    }

    @Override
    @Transactional
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee = employeeRepository.save(employee);
        List<Department> departmentList = new ArrayList<>();
        for (int departmentId : employeeDTO.getDepartments()) {
            departmentList.add(departmentService.findDepartmentById(departmentId));
        }
        empDepartService.save(employee, departmentList);
    }
    public void update(EmployeeDTO employeeDTO,int id){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            //exception
            throw new RuntimeException("Did not find employee with id");
        }else {
            Employee employee=employeeOptional.get();
            employee.setEmail(employeeDTO.getEmail());
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee = employeeRepository.save(employee);
        }
    }
    @Override
    @Transactional
    public void deleteById(int theId) {
        departmentService.deleteById(theId);
        employeeRepository.deleteById(theId);
    }
}
