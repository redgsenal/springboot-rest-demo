package com.myrestdemo.restdemo.repository;

import com.myrestdemo.restdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
