package com.github.jvanheesch;

public class EmployeeDto {
    private final Long myId;
    private final String name;

    public EmployeeDto(Long myId, String name) {
        this.myId = myId;
        this.name = name;
    }

    public Long getMyId() {
        return myId;
    }

    public String getName() {
        return name;
    }
}
