package com.example.analysis.controller;

import com.example.analysis.model.Project;
import com.example.analysis.model.Vulnerability;
import com.example.analysis.repository.ProjectRepository;
import com.example.analysis.repository.VulnerabilityRepository;
import com.example.analysis.service.FortifyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final VulnerabilityRepository vulnerabilityRepository;
    private final FortifyService fortifyService;

    public ProjectController(ProjectRepository projectRepository, VulnerabilityRepository vulnerabilityRepository, FortifyService fortifyService) {
        this.projectRepository = projectRepository;
        this.vulnerabilityRepository = vulnerabilityRepository;
        this.fortifyService = fortifyService;
    }

    @GetMapping
    public List<Project> list() {
        return projectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(201).body(projectRepository.save(project));
    }

    @PostMapping("/{id}/scan")
    public ResponseEntity<?> scan(@PathVariable Long id) throws Exception {
        Project project = projectRepository.findById(id).orElseThrow();
        fortifyService.runScan(project);
        return ResponseEntity.accepted().body(Map.of("jobId", id));
    }

    @GetMapping("/{id}/results")
    public ResponseEntity<?> results(@PathVariable Long id) {
        List<Vulnerability> list = vulnerabilityRepository.findByProjectId(id);
        return ResponseEntity.ok(Map.of("vulnerabilities", list));
    }
}
