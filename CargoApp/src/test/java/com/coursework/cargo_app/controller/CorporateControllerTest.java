package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.Corporate;
import com.coursework.cargo_app.service.CorporateService;
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
@WebMvcTest(value = CorporateController.class)
@WithMockUser
class CorporateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CorporateService corporateService = new CorporateService();

    String exampleCorporateJson = "{   \n" +
            "    \"email\" : \"powergym@rmail.com\",\n" +
            "    \"phone\" : \"+74957821535\",\n" +
            "    \"company_name\" : \"ООО 'Спорт-Зал'\",\n" +
            "    \"legal_address\" : \"г. Москва, м. Сокол, 2-я Песчаная ул., д. 4Ас2\"\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        Corporate mockCorporate = new Corporate();

        Mockito.when(
                corporateService.find(Mockito.any(Corporate.class).getId())).thenReturn(java.util.Optional.of(mockCorporate));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/corporates")
                .accept(MediaType.APPLICATION_JSON).content(exampleCorporateJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/corporates",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        Corporate corporate1 = new Corporate();
        Corporate corporate2 = new Corporate();
        Corporate corporate3 = new Corporate();

        ArrayList<Corporate> corporateList = new ArrayList<Corporate>();

        corporateList.add(corporate1);
        corporateList.add(corporate2);
        corporateList.add(corporate3);

        Mockito.when(
                corporateService.findAll()).thenReturn(corporateList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/corporates")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(corporateList, response);
    }

    @Test
    void testFind() throws Exception{
        Corporate corporate = new Corporate();

        Mockito.when(
                corporateService.find(corporate.getId())).thenReturn(java.util.Optional.of(corporate));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/corporates/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(corporate, response);
    }
}