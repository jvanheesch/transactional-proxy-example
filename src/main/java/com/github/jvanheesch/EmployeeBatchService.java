package com.github.jvanheesch;

import java.util.List;

public interface EmployeeBatchService {
    void saveBatch(List<EmployeeDto> employees);
}
