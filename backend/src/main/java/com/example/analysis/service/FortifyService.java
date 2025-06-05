package com.example.analysis.service;

import com.example.analysis.model.Project;
import com.example.analysis.model.Vulnerability;
import com.example.analysis.repository.VulnerabilityRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FortifyService {
    private final VulnerabilityRepository vulnerabilityRepository;

    public FortifyService(VulnerabilityRepository vulnerabilityRepository) {
        this.vulnerabilityRepository = vulnerabilityRepository;
    }

    @Async
    public void runScan(Project project) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("sourceanalyzer", "-b", "mybuild", project.getPath());
        pb.inheritIO();
        pb.start();

        // Dummy 결과 저장
        Vulnerability v = new Vulnerability();
        v.setCategory("SQL Injection");
        v.setSeverity("High");
        v.setProject(project);
        vulnerabilityRepository.save(v);
    }
}
