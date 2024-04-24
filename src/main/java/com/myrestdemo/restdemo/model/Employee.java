package com.myrestdemo.restdemo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.lang.annotation.Repeatable;
import java.util.Comparator;

@Entity
@Table(name="employees")
public class Employee implements Comparable<Employee> {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;

    public Employee(){}

    public Employee(String id, String firstName, String lastName, String email, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
