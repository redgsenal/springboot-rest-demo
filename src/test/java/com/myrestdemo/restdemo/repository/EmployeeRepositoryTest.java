package com.myrestdemo.restdemo.repository;

import com.myrestdemo.restdemo.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        Employee empl = employees.get(0);
        assertThat(empl).isNotNull();
        assertThat(empl.getCountry()).isEqualTo(employee.getCountry());
        assertThat(empl.getEmail()).isEqualTo(employee.getEmail());
    }

    // Do FAIL Test Case
    @Test
    void testFindByEmployeeNameNotFound(){
        List<Employee> employees = employeeRepository.findByCountry("Singapore");
        assertThat(!employees.isEmpty()).isTrue();
    }
}
