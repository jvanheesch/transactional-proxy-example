package com.github.jvanheesch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final JmsTemplate jmsTemplate;

    @Autowired
    private EmployeeService employeeService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, JmsTemplate jmsTemplate) {
        this.employeeRepository = employeeRepository;
        this.jmsTemplate = jmsTemplate;
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

    @Transactional
    @Override
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setId(dto.getMyId());
        employee.setName(dto.getName());
        Employee saved = employeeRepository.save(employee);
        return new EmployeeDto(saved.getId(), saved.getName());
    }

    @Override
    public void testTransactionalityAmq() {
        jmsTemplate.send("opdrachtenMailbox.queue", session -> session.createTextMessage("test transactionality"));
        throw new RuntimeException();
    }
}
