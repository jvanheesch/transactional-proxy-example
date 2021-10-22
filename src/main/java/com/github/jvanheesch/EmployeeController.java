package com.github.jvanheesch;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeBatchService employeeBatchService;

    public EmployeeController(EmployeeService employeeService, EmployeeBatchService employeeBatchService) {
        this.employeeService = employeeService;
        this.employeeBatchService = employeeBatchService;
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

    @GetMapping("/employees/batch")
    public void saveBatch(@RequestBody List<EmployeeDto> employees) {
        this.employeeBatchService.saveBatch(employees);
    }
}
