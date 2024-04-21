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

    @PostMapping
    public String createEmployee(@RequestBody Employee employee) {
        if (employees == null){
            employees = new ArrayList<Employee>();
        }
        if (employees.contains(employee)){
            return "invalid employee entry";
        }
        employees.add(employee);
        return "new employee record created";
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

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable("id") String id){
        if (StringUtils.isEmpty(id)){
            return "invalid employee id";
        }
        Employee employee =  getEmployeeDetails(id);
        if (employee == null){
            return "invalid employee id";
        }
        employees.remove(employee);
        return "employee id deleted";
    }

    private boolean InvalidEmployee(Employee employee){
        return ((employees == null) || !employees.contains(employee));
    }
}