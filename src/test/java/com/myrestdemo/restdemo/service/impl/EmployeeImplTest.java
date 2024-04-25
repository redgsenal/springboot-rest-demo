package com.myrestdemo.restdemo.service.impl;

import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.repository.EmployeeRepository;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService = employeeService = new EmployeeImpl(employeeRepository);

    private AutoCloseable autoCloseable;
    private Employee employee;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        employee = new Employee("6", "John", "Doe", "jd@demo.com", "Singapore");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.save(employee)).thenReturn(employee);
        String actual = employeeService.addEmployee(employee);
        assertThat(actual).isEqualTo("Success");
    }

    @Test
    void testUpdateEmployee() {
    }

    @Test
    void testDeleteEmployee() {
    }

    @Test
    void testGetEmployee() {
    }

    @Test
    void testGetAllEmployees() {
    }

    @Test
    void testGetEmployeeCountry() {
    }
}