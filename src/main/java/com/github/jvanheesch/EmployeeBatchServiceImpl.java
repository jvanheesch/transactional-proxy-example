package com.github.jvanheesch;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeBatchServiceImpl implements EmployeeBatchService {
    private final EmployeeService employeeService;

    public EmployeeBatchServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Transactional
    @Override
    public void saveBatch(List<EmployeeDto> employees) {
        for (EmployeeDto employee : employees) {
            employeeService.save(employee);
        }

        if (employees.stream().anyMatch(e -> e.getName().equals("invalid"))) {
            throw new RuntimeException();
        }
    }
}
