package com.example.pma.controllers;

import com.example.pma.entities.Employee;
import com.example.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by George Fouche on 11/25/19.
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = empService.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {

        Employee anEmployee = new Employee();

        model.addAttribute("employee", anEmployee);

        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee) {
        // save to the database using an employee crud repository
        empService.save(employee);
        return "redirect:/employees/new";
    }
}
