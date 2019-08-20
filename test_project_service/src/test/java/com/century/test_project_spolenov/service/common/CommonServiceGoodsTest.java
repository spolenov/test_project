package com.century.test_project_spolenov.service.common;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.repository.goods.GoodsRepository;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.response.Response;
import com.century.test_project_spolenov.service.response.ResponseState;
import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class CommonServiceGoodsTest {
    @InjectMocks
    private CommonServiceImpl<Goods> goodsService;
    @Mock
    private GoodsRepository goodsRepository;

    private Goods goods;
    private List<Goods> goodsList = new ArrayList<>();
    private BaseRequest<Goods> goodsRequest;

    private static final String EXCEPTION_MESSAGE = "Test exception";

    @BeforeEach
    private void initBeforeEach(){
        goodsService.init();

        final int orderId = 5;

        goods = new Goods();
        goods.setId(orderId);
        goods.setPrice(new BigDecimal(2.1234567));
        goods.setName("test_goods1");
        goodsRequest = new BaseRequest<>(goods);

        Goods nextGoods = new Goods();
        nextGoods.setId(orderId + 1);
        nextGoods.setPrice(new BigDecimal(1.1234567));
        nextGoods.setName("test_goods2");

        goodsList.add(goods);
        goodsList.add(nextGoods);

        when(goodsRepository.findAll()).thenReturn(goodsList);
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.of(goods));
    }

    @Test
    void addNewTest(){
        Goods element = goodsRequest.getSingleElement();

        //Не нашли элемент в БД, можно сохранить новый элемент.
        when(goodsRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.empty());

        Response response = goodsService.addNew(goodsRequest);
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(goodsRepository, times(1))
                .findById(eq(element.getId()));
        verify(goodsRepository, times(1))
                .save(eq(element));
        reset(goodsRepository);

        //Такой элемент уже есть в БД, нельзя сохранить новый элемент.
        when(goodsRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.of(element));
        response = goodsService.addNew(goodsRequest);
        assertNotEquals(response, ActionResponse.okResponse());

        verify(goodsRepository, times(1))
                .findById(eq(element.getId()));
        verify(goodsRepository, never())
                .save(eq(element));
        reset(goodsRepository);

        //Нужно обновить только одни элемент, а передали больше? Должна быть ошибка.
        goodsRequest.setData(goodsList);
        response = goodsService.addNew(goodsRequest);

        verify(goodsRepository, never())
                .findById(eq(element.getId()));
        verify(goodsRepository, never())
                .save(eq(element));

        assertNotEquals(response, ActionResponse.okResponse());
        assertFalse(response.getDescription().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
    }

    @Test
    void deleteOneTest(){
        Goods element = goodsRequest.getSingleElement();

        Response response = goodsService.deleteOne(goods.getId());
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(goodsRepository, times(1))
                .findById(eq(element.getId()));
        verify(goodsRepository, times(1))
                .deleteById(eq(element.getId()));
        reset(goodsRepository);

        //Не нашли элемент в БД? Удаление невозможно.
        when(goodsRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.empty());

        response = goodsService.deleteOne(goods.getId());
        assertNotEquals(response, ActionResponse.okResponse());

        verify(goodsRepository, times(1))
                .findById(eq(element.getId()));
        verify(goodsRepository, never())
                .deleteById(eq(element.getId()));
        reset(goodsRepository);
    }

    @Test
    void updateOneTest(){
        Response response = goodsService.updateOne(goodsRequest);
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(goodsRepository, times(1))
                .findById(eq(goodsRequest.getSingleElement().getId()));
        verify(goodsRepository, times(1))
                .save(eq(goodsRequest.getSingleElement()));
        reset(goodsRepository);

        //Не нашли элемент в БД? Обновление невозможно.
        when(goodsRepository
                .findById(eq(goodsRequest.getSingleElement().getId())))
                .thenReturn(Optional.empty());
        response = goodsService.updateOne(goodsRequest);
        assertNotEquals(response, ActionResponse.okResponse());

        verify(goodsRepository, times(1))
                .findById(eq(goodsRequest.getSingleElement().getId()));
        verify(goodsRepository, never())
                .save(eq(goodsRequest.getSingleElement()));
        reset(goodsRepository);

        //Нужно обновить только одни элемент, а передали больше? Должна быть ошибка.
        goodsRequest.setData(goodsList);
        response = goodsService.updateOne(goodsRequest);

        verify(goodsRepository, never())
                .findById(eq(goodsRequest.getSingleElement().getId()));
        verify(goodsRepository, never())
                .save(eq(goodsRequest.getSingleElement()));

        assertNotEquals(response, ActionResponse.okResponse());
        assertFalse(response.getDescription().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
    }

    @Test
    void getByIdTest(){
        Response response = goodsService.getById(goods.getId());
        assertNotNull(response.getData());

        assertEquals(response.getData().get(0), goods);
        verify(goodsRepository, times(1))
                .findById(eq(goods.getId()));
    }

    @Test
    void getAllTest(){
        Response response = goodsService.getAll();
        assertFalse(response.getData().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        when(goodsRepository.findAll()).thenReturn(new ArrayList<>());
        response = goodsService.getAll();

        assertTrue(response.getData().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertFalse(response.getDescription().isEmpty());
    }

    @Test
    void getAllOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(goodsRepository)
                .findAll();
        Response response = goodsService.getAll();
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void getByIdOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(goodsRepository)
                .findById(eq(goods.getId()));
        Response response = goodsService.getById(goods.getId());
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void addNewOnExceptionTest(){
        when(goodsRepository.findById(goods.getId())).thenReturn(Optional.empty());

        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(goodsRepository)
                .save(eq(goods));
        Response response = goodsService.addNew(goodsRequest);
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void updateOneOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(goodsRepository)
                .save(eq(goods));
        Response response = goodsService.updateOne(goodsRequest);
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void deleteOneOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(goodsRepository)
                .deleteById(eq(goods.getId()));
        Response response = goodsService.deleteOne(goods.getId());
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }
}

