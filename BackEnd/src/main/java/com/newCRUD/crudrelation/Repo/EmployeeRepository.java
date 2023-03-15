package com.newCRUD.crudrelation.Repo;
import com.newCRUD.crudrelation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
