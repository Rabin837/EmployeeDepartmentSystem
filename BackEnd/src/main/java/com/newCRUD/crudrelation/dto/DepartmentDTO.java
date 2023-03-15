package com.newCRUD.crudrelation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO {
    private int department_id;
    private String department_Name;
    private List<EmployeeDTO> employees;
}
