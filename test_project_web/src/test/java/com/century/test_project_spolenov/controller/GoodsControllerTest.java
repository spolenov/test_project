package com.century.test_project_spolenov.controller;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.service.goods.GoodsService;
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
import java.util.List;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static com.century.test_project_spolenov.util.TestDeserializeUtils.*;
import static com.century.test_project_spolenov.util.TestMockMvcFactory.getMockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/test-context.xml"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.Random.class)
class GoodsControllerTest {
    @InjectMocks
    private GoodsController goodsController;
    @Mock
    private GoodsService goodsService;

    private MockMvc mockMvc;
    
    private static final String SERVLET_PATH = "/rest/goods/";
    private static final String DELETE_PATH = "delete/";
    
    private static final int GOODS_ID = 5;
    private static final int FAKE_GOODS_ID = 1000;

    private static final String GOODS_NAME = "test_goods_name";
    private static final String OK_STATUS = "OK";
    private static final String ERR_STATUS = "ERROR";
    private static final String EXCEPTION_MSG = "test_exception_message";
    private static final String NO_GOODS_MSG = "test_no_such_goods_message";

    private final List<Goods> testData = new ArrayList<>();
    private final Goods goods = new Goods();

    private BaseRequest<Goods> request = new BaseRequest<>(new ArrayList<Goods>(){{add(goods);}});
    private ArgumentCaptor<BaseRequest> captor = ArgumentCaptor.forClass(BaseRequest.class);

    @BeforeAll
    void setup()
    {
        mockMvc = getMockMvc(goodsController);
        MockitoAnnotations.initMocks(this);
        goods.setId(GOODS_ID);
        goods.setName(GOODS_NAME);
        goods.setPrice(BigDecimal.valueOf (1.1234567d));

        testData.add(goods);
    }

    @BeforeEach
    void beforeEach(){
        Mockito.reset(goodsService);
        when(goodsService.getAll()).thenReturn(okResponse(testData));
        when(goodsService.deleteOne(GOODS_ID)).thenReturn(okResponse());
        when(goodsService.deleteOne(FAKE_GOODS_ID)).thenReturn(errResponse(NO_GOODS_MSG));
        when(goodsService.addNew(any(BaseRequest.class))).thenReturn(okResponse());
        when(goodsService.updateOne(any(BaseRequest.class))).thenReturn(okResponse());
        when(goodsService.getById(GOODS_ID)).thenReturn(okResponse(testData));
        when(goodsService.getById(FAKE_GOODS_ID)).thenReturn(errResponse(NO_GOODS_MSG));
    }

    @Test
    void getAllTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + "all")
                .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).getAll();
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(goods, deserializeGoods(json));

        assertEquals(
                deserializeNode(json, STATE_FIELD, String.class),
                OK_STATUS);
    }

    @Test
    void getAllExeptionTest() throws Exception {
        final List<String> responses = new ArrayList<>();

        doThrow(new RuntimeException(EXCEPTION_MSG))
                .when(goodsService).getAll();

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + "all")
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));
        verify(goodsService, times(1)).getAll();

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
                mockMvc.perform(get(SERVLET_PATH + GOODS_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).getById(eq(GOODS_ID));
        String json = responses.get(0);

        assertNotNull(json);
        assertFalse(json.isEmpty());

        assertEquals(goods, deserializeGoods(json));

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

        verify(goodsService, times(1)).getById(eq(FAKE_GOODS_ID));
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
                .when(goodsService).getById(anyInt());

        expectCommon(
                mockMvc.perform(get(SERVLET_PATH + GOODS_ID)
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).getById(eq(GOODS_ID));

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
                mockMvc.perform(delete(SERVLET_PATH + DELETE_PATH + GOODS_ID)
                        .contentType(APPLICATION_JSON)), false)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).deleteOne(GOODS_ID);
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

        verify(goodsService, times(1)).deleteOne(FAKE_GOODS_ID);
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
                .when(goodsService).deleteOne(anyInt());

        expectCommon(
                mockMvc.perform(delete(SERVLET_PATH + DELETE_PATH + GOODS_ID)
                        .contentType(APPLICATION_JSON)), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).deleteOne(eq(GOODS_ID));

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

        verify(goodsService, times(1)).updateOne(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Goods>>(){}));

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
                .when(goodsService)
                .updateOne(any(BaseRequest.class));

        expectCommon(
                mockMvc.perform(post(SERVLET_PATH + "update/one")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).updateOne(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Goods>>(){}));

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

        verify(goodsService, times(1)).addNew(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Goods>>(){}));

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
                .when(goodsService)
                .addNew(any(BaseRequest.class));

        expectCommon(
                mockMvc.perform(put(SERVLET_PATH + "add")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(request))), true)
                .andDo(mvcResult -> setJson(responses, mvcResult));

        verify(goodsService, times(1)).addNew(captor.capture());

        assertEquals(request.getData(), convertObject(
                captor.getValue().getData(),
                new TypeReference<ArrayList<Goods>>(){}));

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

    private Goods deserializeGoods(String responseJson) throws IOException {
        JsonNode data = getNode(responseJson, "data");

        return deserializeObject(
                data.get(0).toString(), Goods.class);
    }

}
