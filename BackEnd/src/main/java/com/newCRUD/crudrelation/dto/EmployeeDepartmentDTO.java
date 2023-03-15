package com.newCRUD.crudrelation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDepartmentDTO {
    private int id;
    private  int department_id;
    private int employee_id;

}
