package com.newCRUD.crudrelation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Integer> departments;
    private List<DepartmentDTO> allDepartments;
}
