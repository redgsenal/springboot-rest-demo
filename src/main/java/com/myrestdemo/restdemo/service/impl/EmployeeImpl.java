package com.myrestdemo.restdemo.service.impl;

import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.repository.EmployeeRepository;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String createEmployee(Employee employee) {
        /* TODO put business logic here */
        employeeRepository.save(employee);
        return "New employee created.";
    }

    @Override
    public String updateEmployee(Employee employee) {
        /* TODO put business logic here */
        employeeRepository.save(employee);
        return "Employee record updated.";
    }

    @Override
    public String deleteEmployee(String id) {
        /* TODO put business logic here */
        employeeRepository.deleteById(id);
        return "Employee record deleted.";
    }

    @Override
    public Employee getEmployee(String id) {
        /* TODO put business logic here */
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        /* TODO put business logic here */
        return employeeRepository.findAll();
    }
}
