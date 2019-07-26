package com.century.test_project_spolenov.controller;

import com.century.test_project_spolenov.model.client.Client;
import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.order.OrderService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.util.MockitoExtension;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static com.century.test_project_spolenov.util.TestDeserializeUtils.*;
import static com.century.test_project_spolenov.util.TestMockMvcFactory.getMockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/test-context.xml"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Random.class)
class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;
    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;

    private static final String SERVLET_PATH = "/rest/order/";
    private static final String DELETE_PATH = "delete/";

    private static final int ORDER_ID = 5;
    private static final int FAKE_GOODS_ID = 1000;

    private static final String TEST_ADDRESS = "test_address";
    private static final String OK_STATUS = "OK";
    private static final String ERR_STATUS = "ERROR";
    private static final String EXCEPTION_MSG = "test_exception_message";
    private static final String NO_GOODS_MSG = "test_no_such_order_message";

    private final List<Order> testData = new ArrayList<>();
    private final Order order = new Order();

    private BaseRequest<Order> request = new BaseRequest<>(new ArrayList<Order>(){{add(order);}});
    private ArgumentCaptor<BaseRequest> captor = ArgumentCaptor.forClass(BaseRequest.class);

    @BeforeAll
    void setup()
    {
        mockMvc = getMockMvc(orderController);
        MockitoAnnotations.initMocks(this);
        order.setId(ORDER_ID);
        order.setOrderDate(new Date());
        order.setClient(new Client());
        order.setAddress(TEST_ADDRESS);
        testData.add(order);
    }

    @BeforeEach
    void beforeEach(){
        Mockito.reset(orderService);
        when(orderService.getAll()).thenReturn(okResponse(testData));
        when(orderService.deleteOne(ORDER_ID)).thenReturn(okResponse());
        when(orderService.deleteOne(FAKE_GOODS_ID)).thenReturn(errResponse(NO_GOODS_MSG));
        when(orderService.addNew(any(BaseRequest.class))).thenReturn(okResponse());
        when(orderService.updateOne(any(BaseRequest.class))).thenReturn(okResponse());
        when(orderService.getById(ORDER_ID)).thenReturn(okResponse(testData));
        when(orderService.getById(FAKE_GOODS_ID)).thenReturn(errResponse(NO_GOODS_MSG));
    }

    @Test
    void getAllTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + "all")
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).getAll();
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(order, deserializeOrder(json));

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void getAllExeptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(orderService).getAll();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + "all")
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));
        verify(orderService, times(1)).getAll();

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(EXCEPTION_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void getByIdExistingTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + ORDER_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).getById(eq(ORDER_ID));
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(order, deserializeOrder(json));

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void getByIdNotExistingTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + FAKE_GOODS_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).getById(eq(FAKE_GOODS_ID));
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(NO_GOODS_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void getByIdExceptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(orderService).getById(anyInt());

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + ORDER_ID)
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).getById(eq(ORDER_ID));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(EXCEPTION_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void deleteExistingTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(delete(SERVLET_PATH + DELETE_PATH + ORDER_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).deleteOne(ORDER_ID);
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void deleteNotExistingTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(delete(SERVLET_PATH + DELETE_PATH + FAKE_GOODS_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).deleteOne(FAKE_GOODS_ID);
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(NO_GOODS_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void deleteExceptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(orderService).deleteOne(anyInt());

        expectCommon(
                mockMvc.perform(delete(SERVLET_PATH + DELETE_PATH + ORDER_ID)
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).deleteOne(eq(ORDER_ID));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(EXCEPTION_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void updateOneTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(post(SERVLET_PATH + "update/one")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).updateOne(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Order>>(){}));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void updateOneExceptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(orderService)
                .updateOne(any(BaseRequest.class));

        expectCommon(
                mockMvc.perform(post(SERVLET_PATH + "update/one")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).updateOne(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Order>>(){}));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(EXCEPTION_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    @Test
    void addNewTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(put(SERVLET_PATH + "add")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).addNew(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Order>>(){}));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void addNewExceptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(orderService)
                .addNew(any(BaseRequest.class));

        expectCommon(
                mockMvc.perform(put(SERVLET_PATH + "add")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(orderService, times(1)).addNew(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Order>>(){}));

        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertTrue(
                deserializeNode(json, DESCR_FIELD, String.class)
                        .contains(EXCEPTION_MSG));

        assertEquals(
                deserializeData(json, ArrayList.class),
                new ArrayList<>());

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                ERR_STATUS);
    }

    private Order deserializeOrder(String responseJson) throws IOException {
        JsonNode data = getNode(responseJson, "data");

        return deserializeObject(
                data.get(0).toString(), Order.class);
    }

}
