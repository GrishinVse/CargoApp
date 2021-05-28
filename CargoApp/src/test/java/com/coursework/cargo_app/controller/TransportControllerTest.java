package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Transport;
import com.coursework.cargo_app.service.TransportService;
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
@WebMvcTest(value = TransportController.class)
@WithMockUser
class TransportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TransportService transportService = new TransportService();

    String exampleTransportJson = "{   \n" +
            "    \"brand\" : \"Mercedes-Benz Sprinter\",\n" +
            "    \"capacity\" : 10.4,\n" +
            "    \"carrying\" : 1500.0,\n" +
            "    \"licence_plate\" : \"А014БА77\"\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        Transport mockTransport = new Transport();

        Mockito.when(
                transportService.find(Mockito.any(Transport.class).getId())).thenReturn(java.util.Optional.of(mockTransport));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/transports")
                .accept(MediaType.APPLICATION_JSON).content(exampleTransportJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/transports",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        Transport trans1 = new Transport();
        Transport trans2 = new Transport();
        Transport trans3 = new Transport();

        ArrayList<Transport> transportList = new ArrayList<Transport>();

        transportList.add(trans1);
        transportList.add(trans2);
        transportList.add(trans3);

        Mockito.when(
                transportService.findAll()).thenReturn(transportList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/transports")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(transportList, response);
    }

    @Test
    void testFind() throws Exception{
        Transport trans1 = new Transport();

        Mockito.when(
                transportService.find(trans1.getId())).thenReturn(java.util.Optional.of(trans1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/transports/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(trans1, response);
    }

}