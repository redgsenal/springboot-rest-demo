package com.myrestdemo.restdemo.controller;

import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeAPIController {

    private final EmployeeService employeeService;

    public EmployeeAPIController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * List all employees
     *
     * @return List
     */
    @GetMapping("/list")
    public List<Employee> getEmployeeList() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * Get specific employee details
     *
     * @param id
     * @return employee
     */
    @GetMapping("/{id}")
    public Employee getEmployeeDetails(@PathVariable("id") String id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping
    public String updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        return employeeService.deleteEmployee(id);
    }
}