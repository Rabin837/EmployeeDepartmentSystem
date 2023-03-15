//Creating Rest Controller to use DAO
package com.newCRUD.crudrelation.rest;

import com.newCRUD.crudrelation.dto.EmployeeDTO;
import com.newCRUD.crudrelation.entity.Employee;
import com.newCRUD.crudrelation.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService)
    {
         employeeService=theEmployeeService;
     }

     @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //Read a single employee
    @GetMapping("/employees/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable int employeeId) {
        EmployeeDTO theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id with" + employeeId + " not found!!!");
        }
        return theEmployee;
    }
    //Add a new employee
    @PostMapping("/employees")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.save(employeeDTO);
        return employeeDTO;
    }

    //Update an employee
    @PutMapping("/employees/{id}")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable int id){
         employeeService.update(employeeDTO,id);
         return employeeDTO;
    }

    //Delete an employee by employeeId
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

         EmployeeDTO tempEmployee= employeeService.findById(employeeId);

         //throw exception if null
        if(tempEmployee==null){
            throw new RuntimeException("Employee Id with "+ employeeId+" is not found");
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee with id "+ employeeId;
    }
}







