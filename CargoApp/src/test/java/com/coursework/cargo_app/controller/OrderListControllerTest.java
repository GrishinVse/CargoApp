package com.coursework.cargo_app.controller;

import com.coursework.cargo_app.entity.OrderList;
import com.coursework.cargo_app.service.OrderListService;
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
@WebMvcTest(value = OrderListController.class)
@WithMockUser
class OrderListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderListService orderListService = new OrderListService();

    String exampleOrderListJson = "{   \n" +
            "    \"start_address\" : \"Бутырская, 46 ст1\",\n" +
            "    \"other_address\" : \"Авиационный переулок, 8\",\n" +
            "    \"end_address\" : \"Адмирала Макарова, 19 к1\",\n" +
            "    \"order_type\" : \"WORKING\",\n" +
            "    \"description\" : \"Перевозка документации между офисами компании.\"\n" +
            "}";

    @Test
    void testCreate() throws Exception {
        OrderList mockOrderList = new OrderList();

        Mockito.when(
                orderListService.find(Mockito.any(OrderList.class).getId())).thenReturn(java.util.Optional.of(mockOrderList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/cargo_app/orderLists")
                .accept(MediaType.APPLICATION_JSON).content(exampleOrderListJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/cargo_app/orderLists",
                response.getHeader(HttpHeaders.LOCATION));
    }


    @Test
    void testFindAll() throws Exception {
        OrderList orderList1 = new OrderList();
        OrderList orderList2 = new OrderList();
        OrderList orderList3 = new OrderList();

        ArrayList<OrderList> orderListList = new ArrayList<OrderList>();

        orderListList.add(orderList1);
        orderListList.add(orderList2);
        orderListList.add(orderList3);

        Mockito.when(
                orderListService.findAll()).thenReturn(orderListList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/orderLists")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(orderListList, response);
    }

    @Test
    void testFind() throws Exception{
        OrderList orderList = new OrderList();

        Mockito.when(
                orderListService.find(orderList.getId())).thenReturn(java.util.Optional.of(orderList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/cargo_app/orderLists/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(orderList, response);
    }
}