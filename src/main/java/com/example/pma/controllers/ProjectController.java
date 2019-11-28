package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import com.example.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectService projService;

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = projService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}

	@GetMapping("/new")
	public String displayProjectForm(Model model) {

		Project aProject = new Project();
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees", employees);

		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {

		projService.save(project);

		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";

	}

}
