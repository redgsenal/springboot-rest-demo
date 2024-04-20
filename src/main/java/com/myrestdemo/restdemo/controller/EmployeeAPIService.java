package com.myrestdemo.restdemo.controller;

import com.myrestdemo.restdemo.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeAPIService {

    private List<Employee> employees;

    @GetMapping("/list")
    public List<Employee> getEmployeeList() {
        if (employees == null){
            employees = new ArrayList<Employee>();
        }
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeDetails(@PathVariable("id") String id) {
        if (CollectionUtils.isEmpty(employees) || StringUtils.isEmpty(id)) {
            return null;
        }
        for (Employee employee : employees) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        return null;
    }

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        if (employees == null){
            employees = new ArrayList<Employee>();
        }
        if (isInvalidEmployee(employee)){
            return "invalid employee entry";
        }
        employees.add(employee);
        return "new employee record created";
    }

    @PutMapping
    public String updateEmployee(@RequestBody Employee employee) {
        if (employees == null){
            employees = new ArrayList<Employee>();
            return "employees list is empty";
        }
        if (CollectionUtils.isEmpty(employees) || !employees.contains(employee)){
            return "employee not found";
        }
        employees.set(employees.indexOf(employee), employee);
        return "employee record edit";
    }

    private boolean isInvalidEmployee(Employee employee){
        return ((employees == null) || employees.contains(employee));
    }
}