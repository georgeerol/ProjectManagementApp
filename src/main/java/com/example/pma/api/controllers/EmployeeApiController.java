package com.example.pma.api.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by George Fouche on 12/1/19.
 */
@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public List<Employee> getEmployees() {
        return empRepo.findAll();

    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable("id") Long id) {
        return empRepo.findById(id).get();

    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody  @Valid Employee employee) {
        return empRepo.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody Employee employee) {
        return empRepo.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody Employee patchEmp) {
        Employee emp = empRepo.findById(id).get();

        if (patchEmp.getEmail() != null) {
            emp.setEmail(patchEmp.getEmail());
        }
        if (patchEmp.getFirstName() != null) {
            emp.setFirstName(patchEmp.getFirstName());
        }

        if (patchEmp.getLastName() != null) {
            emp.setLastName(patchEmp.getLastName());
        }

        return empRepo.save(emp);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        empRepo.deleteById(id);
    }

}
