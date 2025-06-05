package com.example.analysis.controller;

import com.example.analysis.model.VulnerabilityCategory;
import com.example.analysis.repository.VulnerabilityCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vulnerabilities")
public class CategoryController {
    private final VulnerabilityCategoryRepository repository;

    public CategoryController(VulnerabilityCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/categories")
    public List<String> categories() {
        return repository.findAll().stream().map(VulnerabilityCategory::getName).toList();
    }
}
