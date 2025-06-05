package com.example.analysis.config;

import com.example.analysis.model.User;
import com.example.analysis.model.VulnerabilityCategory;
import com.example.analysis.repository.UserRepository;
import com.example.analysis.repository.VulnerabilityCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initData(UserRepository userRepository, VulnerabilityCategoryRepository categoryRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (categoryRepository.count() == 0) {
                String[] names = {"OWASP Top 10", "PCI DSS", "CWE"};
                for (String n : names) {
                    VulnerabilityCategory c = new VulnerabilityCategory();
                    c.setName(n);
                    categoryRepository.save(c);
                }
            }
        };
    }
}
