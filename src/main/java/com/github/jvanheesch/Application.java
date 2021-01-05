package com.github.jvanheesch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        Employee maarten = new Employee();
        maarten.setName("Maarten");
        employeeRepository.save(maarten);

        Employee sam = new Employee();
        sam.setName("Sam");
        sam.setCoach(maarten);
        employeeRepository.save(sam);
    }
}
