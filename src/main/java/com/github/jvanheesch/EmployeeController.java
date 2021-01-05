package com.github.jvanheesch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}/without_transaction")
    public Optional<EmployeeDto> getCoachWithoutTransactional(@PathVariable Long id) {
        return this.employeeService.getCoachWithoutTransactional(id);
    }

    @GetMapping("/employees/{id}/with_transaction")
    public Optional<EmployeeDto> getCoachWithTransactional(@PathVariable Long id) {
        return this.employeeService.getCoachWithTransactional(id);
    }

    @GetMapping("/employees/{id}/broken")
    public Optional<EmployeeDto> getCoachBroken(@PathVariable Long id) {
        return this.employeeService.getCoachBroken(id);
    }

    @GetMapping("/employees/{id}/fixed")
    public Optional<EmployeeDto> getCoachFixed(@PathVariable Long id) {
        return this.employeeService.getCoachFixed(id);
    }
}
