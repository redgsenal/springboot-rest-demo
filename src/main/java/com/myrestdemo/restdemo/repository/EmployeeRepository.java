package com.myrestdemo.restdemo.repository;

import com.myrestdemo.restdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findByCountry(String country);
}