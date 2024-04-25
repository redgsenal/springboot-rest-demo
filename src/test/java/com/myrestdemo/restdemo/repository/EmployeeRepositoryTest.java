package com.myrestdemo.restdemo.repository;

import com.myrestdemo.restdemo.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("1", "John", "Doe", "jd@test.com", "Singapore");
        employeeRepository.save(employee);
    }

    @AfterEach
    void tearDown() {
        employee = null;
        employeeRepository.deleteAll();
    }

    // Do SUCCESS Test Case
    @Test
    void testFindByEmployeeNameFound(){
        List<Employee> employees = employeeRepository.findByCountry("Singapore");
        assertThat(employees.isEmpty()).isFalse();
        assertThat(employees.getFirst()).isNotNull();
        assertThat(employees.getFirst().getCountry()).isEqualTo(employee.getCountry());
        assertThat(employees.getFirst().getEmail()).isEqualTo(employee.getEmail());
    }

    // Do FAIL Test Case
    @Test
    void testFindByEmployeeNameNotFound(){
        List<Employee> employees = employeeRepository.findByCountry("Philippines");
        assertThat(employees.isEmpty()).isTrue();
    }
}
