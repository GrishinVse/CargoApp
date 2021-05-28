package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Individual;
import com.coursework.cargo_app.service.IndividualService;
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
@WebMvcTest(value = IndividualController.class)
@WithMockUser
class IndividualControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IndividualService individualService = new IndividualService();

    String exampleIndividualJson = "{   \n" +
            "    \"first_name\" : \"Иван\",\n" +
            "    \"last_name\" : \"Иванов\",\n" +
            "    \"hire_date\" : \"2015-03-31\",\n" +
            "    \"rating\" : 4.9,\n" +
            "    \"job_id\" : null\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        Individual mockIndividual = new Individual();

        Mockito.when(
                individualService.find(Mockito.any(Individual.class).getId())).thenReturn(java.util.Optional.of(mockIndividual));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/individuals")
                .accept(MediaType.APPLICATION_JSON).content(exampleIndividualJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/individuals",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        Individual individual1 = new Individual();
        Individual individual2 = new Individual();
        Individual individual3 = new Individual();

        ArrayList<Individual> individualList = new ArrayList<Individual>();

        individualList.add(individual1);
        individualList.add(individual2);
        individualList.add(individual3);

        Mockito.when(
                individualService.findAll()).thenReturn(individualList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/individuals")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(individualList, response);
    }

    @Test
    void testFind() throws Exception{
        Individual individual = new Individual();

        Mockito.when(
                individualService.find(individual.getId())).thenReturn(java.util.Optional.of(individual));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/individuals/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(individual, response);
    }
}