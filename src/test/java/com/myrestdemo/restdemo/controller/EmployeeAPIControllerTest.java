package com.myrestdemo.restdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myrestdemo.restdemo.model.Employee;
import com.myrestdemo.restdemo.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    void testGetEmployeeList() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        this.mockMvc.perform(get("/employee/list")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testCreateEmployee() throws Exception {
        Employee expectEmployee = employee1;
        String expectJSONString = buildResponseJSONString(employee1);
        JSONObject expectJSONEmployee = new JSONObject(expectJSONString);
        when(employeeService.createEmployee(expectEmployee)).thenReturn(expectEmployee);
        MvcResult result = this.mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(expectJSONString))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        String actualJSONResponse = result.getResponse().getContentAsString();
        JSONObject actualJSONResponseObject = new JSONObject(actualJSONResponse);
        JSONObject actualJSONEmployee = actualJSONResponseObject.getJSONObject("data");
        JSONAssert.assertEquals(actualJSONEmployee, expectJSONEmployee, JSONCompareMode.STRICT);
    }

    @Test
    void getEmployeeDetails() throws Exception {
        when(employeeService.getEmployee("1")).thenReturn(employee1);
        this.mockMvc.perform(get("/employee/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateEmployee() {
    }

    @Test
    void testDeleteEmployee() throws Exception {
        String expectResponse = "Employee record deleted.";
        when(employeeService.getEmployee("1")).thenReturn(employee3);
        when(employeeService.deleteEmployee("1")).thenReturn(expectResponse);
        MvcResult result = this.mockMvc.perform(delete("/employee/1")).andDo(print()).andExpect(status().isOk()).andReturn();
        String actual = result.getResponse().getContentAsString();
        assertThat(actual).isEqualTo(expectResponse);
    }

    private String buildResponseJSONString(Employee employee) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        map.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = map.writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(employee)
                .replaceAll(StringUtils.SPACE, "")
                .replaceAll("\n", "");
    }
}