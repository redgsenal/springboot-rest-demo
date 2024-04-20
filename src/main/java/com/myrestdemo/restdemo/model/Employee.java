package com.myrestdemo.restdemo.model;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public Employee(){}

    public Employee(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int compareTo(Employee o) {
        if (this.getId().equalsIgnoreCase(o.getId())){
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee employee) {
            return employee.getId().equalsIgnoreCase(this.getId());
        }
        return false;
    }
}
