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

import java.util.ArrayList;
import java.util.Collections;
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
        employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.ofNullable(employee));
        Employee actual = employeeService.createEmployee(employee);
        assertThat(actual).isEqualTo(employee);
    }

    @Test
    void testUpdateEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.save(employee)).thenReturn(employee);
        assertThat(employeeService.updateEmployee(employee)).isEqualTo("Employee record updated.");
    }

    @Test
    void testDeleteEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class, CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(employeeRepository).deleteById(any());
        assertThat(employeeService.deleteEmployee("1")).isEqualTo("Employee record deleted.");
    }

    @Test
    void testGetEmployee() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findById("6")).thenReturn(Optional.ofNullable(employee));
        Employee actual = employeeService.getEmployee("6");
        assertThat(actual).isEqualTo(employee);
    }

    @Test
    void testGetAllEmployees() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>(Collections.singleton(employee)));
        Employee actual = employeeService.getAllEmployees().get(0);
        assertThat(actual).isEqualTo(employee);
    }

    @Test
    void testGetEmployeeCountry() {
        mock(Employee.class);
        mock(EmployeeRepository.class);

        when(employeeRepository.findByCountry("Singapore")).thenReturn(new ArrayList<Employee>(Collections.singleton(employee)));
        assertThat(employeeService.getEmployeeCountry("Singapore").get(0).getId()).isEqualTo(employee.getId());
    }
}