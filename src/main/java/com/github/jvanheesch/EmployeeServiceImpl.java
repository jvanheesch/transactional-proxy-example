package com.github.jvanheesch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<EmployeeDto> getCoachWithoutTransactional(Long id) {
        return employeeRepository.findById(id)
                .map(Employee::getCoach)
                .map(employee -> new EmployeeDto(employee.getId(), employee.getName()));
    }

    @Transactional
    @Override
    public Optional<EmployeeDto> getCoachWithTransactional(Long id) {
        return employeeRepository.findById(id)
                .map(Employee::getCoach)
                .map(employee -> new EmployeeDto(employee.getId(), employee.getName()));
    }

    @Override
    public Optional<EmployeeDto> getCoachBroken(Long id) {
        return this.getCoachWithTransactional(id);
    }

    @Override
    public Optional<EmployeeDto> getCoachFixed(Long id) {
        return employeeService.getCoachWithTransactional(id);
    }
}
