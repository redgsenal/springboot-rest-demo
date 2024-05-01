package com.myrestdemo.restdemo.service.impl;

import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.repository.EmployeeRepository;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    private static final Logger logInfo = LoggerFactory.getLogger(EmployeeImpl.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        /* TODO put business logic here */
        logInfo.info("Employee {}", employee.getId());
        employeeRepository.save(employee);
        return employeeRepository.findById(employee.getId()).orElse(null);
    }

    @Override
    public String addEmployee(Employee employee) {
        return createEmployee(employee) != null ? "Success" : "Fail";
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
        logInfo.info("retrieve employee by id: {}", id);
        logInfo.debug("run debug here retrieve employee by id: {}", id);
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        /* TODO put business logic here */
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getEmployeeCountry(String country) {
        return employeeRepository.findByCountry(country);
    }
}
