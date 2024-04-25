package com.myrestdemo.restdemo.service.impl;

import com.myrestdemo.restdemo.exception.EmployeeNotFoundException;
import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.repository.EmployeeRepository;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        /* TODO put business logic here */
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).orElse(null);
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
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        /* TODO put business logic here */
        return employeeRepository.findAll();
    }
}
