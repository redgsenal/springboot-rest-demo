package com.myrestdemo.restdemo.controller;

import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeAPIController.class)
class EmployeeAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    List<Employee> employeeList = new ArrayList<Employee>();

    @BeforeEach
    void setUp() {
        employee1 = new Employee("1", "John", "Doe", "jd@demo.com", "Singapore");
        employee2 = new Employee("2", "Sally", "James", "sj@demo.com", "Malaysia");
        employee3 = new Employee("3", "Tony", "Tones", "tt@demo.com", "Taiwan");
        employee4 = new Employee("4", "Jim", "Pars", "jp@demo.com", "Hongkong");
        employee5 = new Employee("5", "Lyn", "Parks", "lp@demo.com", "Indonesia");
        employeeList = Arrays.asList(employee1, employee2, employee3, employee4, employee5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetEmployeeList() {
    }

    @Test
    void createEmployee() {
    }

    @Test
    void getEmployeeDetails() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}