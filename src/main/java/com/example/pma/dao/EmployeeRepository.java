package com.example.pma.dao;

import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by George Fouche on 11/25/19.
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Override
    public List<Employee> findAll();

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

}

