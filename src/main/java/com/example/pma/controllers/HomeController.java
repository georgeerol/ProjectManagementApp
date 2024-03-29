package com.example.pma.controllers;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.dto.EmployeeProject;
import com.example.pma.entities.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

	@Value("${version}")
	private String ver;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	EmployeeRepository employeeRepository;


	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {

		model.addAttribute("versionNumber", ver);

		// we are querying the database for projects
		List<Project> projects = projectRepository.findAll();
		model.addAttribute("projectsList", projects);

		List<ChartData> projectData = projectRepository.getProjectStatus();

		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]

		model.addAttribute("projectStatusCnt", jsonString);

		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = employeeRepository.employeeProjects();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt);


		return "main/home";

	}
}
