package com.github.jvanheesch;

import java.util.Optional;

public interface EmployeeService {
    Optional<EmployeeDto> getCoachWithoutTransactional(Long id);

    Optional<EmployeeDto> getCoachWithTransactional(Long id);

    Optional<EmployeeDto> getCoachBroken(Long id);

    Optional<EmployeeDto> getCoachFixed(Long id);

    EmployeeDto save(EmployeeDto employee);
}
