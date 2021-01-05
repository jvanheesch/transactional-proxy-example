package com.github.jvanheesch;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    private Employee coach;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getCoach() {
        return coach;
    }

    public void setCoach(Employee coach) {
        this.coach = coach;
    }
}
