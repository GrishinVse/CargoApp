package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Job;
import com.coursework.cargo_app.service.JobService;
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
@WebMvcTest(value = JobController.class)
@WithMockUser
class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JobService jobService = new JobService();

    String exampleJobJson = "{   \n" +
            "    \"title\" : \"Работник\",\n" +
            "    \"min_salary\" : 35000\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        Job mockJob = new Job();

        Mockito.when(
                jobService.find(Mockito.any(Job.class).getId())).thenReturn(java.util.Optional.of(mockJob));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/jobs")
                .accept(MediaType.APPLICATION_JSON).content(exampleJobJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/jobs",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        Job job1 = new Job();
        Job job2 = new Job();
        Job job3 = new Job();

        ArrayList<Job> jobList = new ArrayList<Job>();

        jobList.add(job1);
        jobList.add(job2);
        jobList.add(job3);

        Mockito.when(
                jobService.findAll()).thenReturn(jobList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/jobs")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(jobList, response);
    }

    @Test
    void testFind() throws Exception{
        Job job = new Job();

        Mockito.when(
                jobService.find(job.getId())).thenReturn(java.util.Optional.of(job));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/jobs/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(job, response);
    }
}