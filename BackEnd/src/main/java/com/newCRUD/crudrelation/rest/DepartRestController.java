package com.newCRUD.crudrelation.rest;

import com.newCRUD.crudrelation.dto.DepartmentDTO;
import com.newCRUD.crudrelation.entity.EmployeeDepartment;
import com.newCRUD.crudrelation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartRestController {
    private DepartmentService departmentService;
    @Autowired
    public DepartRestController(DepartmentService theEmployeeService){

        departmentService=theEmployeeService;
    }

    @GetMapping("/departments")
    public List<EmployeeDepartment> findAll(){
        return departmentService.findAll();
    }

    //Read a single employee
    @GetMapping("/departments/{departmentId}")
    public DepartmentDTO getDepartment(@PathVariable int departmentId) {
        DepartmentDTO departmentDTO = departmentService.findById(departmentId);
        if (departmentDTO == null) {
            throw new RuntimeException("Department id with" + departmentId + " not found!!!");
        }
        return departmentDTO;
    }
    //Add a new employee
    @PostMapping("/departments")
    public DepartmentDTO addEmployee(@RequestBody DepartmentDTO departmentDTO){
        departmentService.save(departmentDTO);
        return departmentDTO;
    }

    //Update an employee
    @PutMapping("/departments/{id}")
    public DepartmentDTO updateEmployee(@RequestBody DepartmentDTO departmentDTO,@PathVariable int id){
         departmentService.update(departmentDTO,id);
        return departmentDTO;
    }

    //Delete an employee by departmentId
    @DeleteMapping("/departments/{departmentId}")
    public String deleteEmployee(@PathVariable int departmentId){

        DepartmentDTO tempEmployee= departmentService.findById(departmentId);

        //throw exception if null
        if(tempEmployee==null){
            throw new RuntimeException("Department Id with "+ departmentId+" is not found");
        }
        departmentService.deleteById(departmentId);

        return "Deleted employee with id "+ departmentId;
    }
}
