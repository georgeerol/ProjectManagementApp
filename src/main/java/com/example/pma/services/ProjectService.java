package com.example.pma.services;

import com.example.pma.dao.ProjectRepository;
import com.example.pma.dto.ChartData;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by George Fouche on 11/27/19.
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository proRepo;

    public Project save(Project project) {
        return proRepo.save(project);
    }

    public List<Project> getAll() {
        return proRepo.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return proRepo.getProjectStatus();
    }
}
