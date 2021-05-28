package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Employee;
import com.coursework.cargo_app.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
@WithMockUser
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService = new EmployeeService();

    String exampleEmployeeJson = "{   \n" +
            "    \"first_name\" : \"Иван\",\n" +
            "    \"last_name\" : \"Иванов\",\n" +
            "    \"hire_date\" : \"2015-03-31\",\n" +
            "    \"rating\" : 4.9,\n" +
            "    \"job_id\" : null\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        Employee mockEmployee = new Employee();

        Mockito.when(
                employeeService.find(Mockito.any(Employee.class).getId())).thenReturn(java.util.Optional.of(mockEmployee));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/employees")
                .accept(MediaType.APPLICATION_JSON).content(exampleEmployeeJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/employees",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();

        ArrayList<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Mockito.when(
                employeeService.findAll()).thenReturn(employeeList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/employees")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(employeeList, response);
    }

    @Test
    void testFind() throws Exception{
        Employee employee = new Employee();

        Mockito.when(
                employeeService.find(employee.getId())).thenReturn(java.util.Optional.of(employee));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/employees/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(employee, response);
    }

}