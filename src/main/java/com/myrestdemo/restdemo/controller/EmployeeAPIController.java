package com.myrestdemo.restdemo.controller;

import com.myrestdemo.restdemo.exception.EmployeeException;
import com.myrestdemo.restdemo.exception.EmployeeIDExistsException;
import com.myrestdemo.restdemo.exception.EmployeeNotFoundException;
import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.response.ResponseHandler;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> createEmployee(@RequestBody Employee employee) {
        Employee result = employeeService.getEmployee(employee.getId());
        if (result == null) {
            return ResponseHandler.responseBuilder(
                    "Employee details created",
                    HttpStatus.OK,
                    employeeService.createEmployee(employee)
            );
        } else {
            throw new EmployeeIDExistsException("Invalid Employee data.");
        }
    }

    /**
     * Get specific employee details
     *
     * @param id employee id
     * @return employee
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("id") String id) {
        Employee result = employeeService.getEmployee(id);
        if (result == null) {
            throw new EmployeeNotFoundException("Employee data not found.");
        }
        return ResponseHandler.responseBuilder("Request employee details", HttpStatus.OK, result);
    }

    @PutMapping
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
        validateEmployeeObject(employee.getId());
        return ResponseHandler.responseBuilder(
                "Update employee details",
                HttpStatus.OK,
                employeeService.updateEmployee(employee)
        );
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable("id") String id) {
        validateEmployeeObject(id);
        return employeeService.deleteEmployee(id);
    }

    private void validateEmployeeObject(String id) {
        Employee result = employeeService.getEmployee(id);
        if (result == null) {
            throw new EmployeeNotFoundException("Employee data not found.");
        }
    }
}