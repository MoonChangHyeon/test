package com.example.analysis.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FortifyService {
    @Async
    public void runScan(String projectPath) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("sourceanalyzer", "-b", "mybuild", projectPath);
        pb.inheritIO();
        pb.start();
    }
}
