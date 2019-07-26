package com.century.test_project_spolenov.service.common;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.order.OrderRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class CommonServiceOrderTest {
    @InjectMocks
    private CommonServiceImpl<Order> orderService;
    @Mock
    private OrderRepository orderRepository;

    private Order order;
    private List<Order> orders = new ArrayList<>();
    private BaseRequest<Order> orderRequest;

    private static final String EXCEPTION_MESSAGE = "Test exception";

    @BeforeEach
    private void initBeforeEach(){
        orderService.init();
        final int orderId = 5;

        order = new Order();
        order.setId(orderId);
        order.setAddress("test_address");
        order.setOrderDate(new Date());
        orderRequest = new BaseRequest<>(order);

        Order nextOrder = new Order();
        nextOrder.setId(orderId + 1);
        nextOrder.setAddress("test_address2");
        nextOrder.setOrderDate(new Date());

        orders.add(order);
        orders.add(nextOrder);

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
    }

    @Test
    void addNewTest(){
        Order element = orderRequest.getSingleElement();

        //Не нашли элемент в БД, можно сохранить новый элемент.
        when(orderRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.empty());

        Response response = orderService.addNew(orderRequest);
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(orderRepository, times(1))
                .findById(eq(element.getId()));
        verify(orderRepository, times(1))
                .save(eq(element));
        reset(orderRepository);

        //Такой элемент уже есть в БД, нельзя сохранить новый элемент.
        when(orderRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.of(element));
        response = orderService.addNew(orderRequest);
        assertNotEquals(response, ActionResponse.okResponse());

        verify(orderRepository, times(1))
                .findById(eq(element.getId()));
        verify(orderRepository, never())
                .save(eq(element));
        reset(orderRepository);

        //Нужно обновить только одни элемент, а передали больше? Должна быть ошибка.
        orderRequest.setData(orders);
        response = orderService.addNew(orderRequest);

        verify(orderRepository, never())
                .findById(eq(element.getId()));
        verify(orderRepository, never())
                .save(eq(element));

        assertNotEquals(response, ActionResponse.okResponse());
        assertFalse(response.getDescription().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
    }

    @Test
    void deleteOneTest(){
        Order element = orderRequest.getSingleElement();

        Response response = orderService.deleteOne(order.getId());
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(orderRepository, times(1))
                .findById(eq(element.getId()));
        verify(orderRepository, times(1))
                .deleteById(eq(element.getId()));
        reset(orderRepository);

        //Не нашли элемент в БД? Удаление невозможно.
        when(orderRepository
                .findById(eq(element.getId())))
                .thenReturn(Optional.empty());

        response = orderService.deleteOne(order.getId());
        assertNotEquals(response, ActionResponse.okResponse());

        verify(orderRepository, times(1))
                .findById(eq(element.getId()));
        verify(orderRepository, never())
                .deleteById(eq(element.getId()));
        reset(orderRepository);
    }

    @Test
    void updateOneTest(){
        Response response = orderService.updateOne(orderRequest);
        assertEquals(response, ActionResponse.okResponse());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        verify(orderRepository, times(1))
                .findById(eq(orderRequest.getSingleElement().getId()));
        verify(orderRepository, times(1))
                .save(eq(orderRequest.getSingleElement()));
        reset(orderRepository);

        //Не нашли элемент в БД? Обновление невозможно.
        when(orderRepository
                .findById(eq(orderRequest.getSingleElement().getId())))
                .thenReturn(Optional.empty());
        response = orderService.updateOne(orderRequest);
        assertNotEquals(response, ActionResponse.okResponse());

        verify(orderRepository, times(1))
                .findById(eq(orderRequest.getSingleElement().getId()));
        verify(orderRepository, never())
                .save(eq(orderRequest.getSingleElement()));
        reset(orderRepository);

        //Нужно обновить только одни элемент, а передали больше? Должна быть ошибка.
        orderRequest.setData(orders);
        response = orderService.updateOne(orderRequest);

        verify(orderRepository, never())
                .findById(eq(orderRequest.getSingleElement().getId()));
        verify(orderRepository, never())
                .save(eq(orderRequest.getSingleElement()));

        assertNotEquals(response, ActionResponse.okResponse());
        assertFalse(response.getDescription().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
    }

    @Test
    void getByIdTest(){
        Response response = orderService.getById(order.getId());
        assertNotNull(response.getData());

        assertEquals(response.getData().get(0), order);
        verify(orderRepository, times(1))
                .findById(eq(order.getId()));
    }

    @Test
    void getAllTest(){
        Response response = orderService.getAll();
        assertFalse(response.getData().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.OK);
        assertFalse(response.getDescription().isEmpty());

        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        response = orderService.getAll();

        assertTrue(response.getData().isEmpty());
        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertFalse(response.getDescription().isEmpty());
    }

    @Test
    void getAllOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(orderRepository)
                .findAll();
        Response response = orderService.getAll();
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void getByIdOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(orderRepository)
                .findById(eq(order.getId()));
        Response response = orderService.getById(order.getId());
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void addNewOnExceptionTest(){
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(orderRepository)
                .save(eq(order));
        Response response = orderService.addNew(orderRequest);
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void updateOneOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(orderRepository)
                .save(eq(order));
        Response response = orderService.updateOne(orderRequest);
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }

    @Test
    void deleteOneOnExceptionTest(){
        doThrow(new RuntimeException(EXCEPTION_MESSAGE))
                .when(orderRepository)
                .deleteById(eq(order.getId()));
        Response response = orderService.deleteOne(order.getId());
        assertTrue(response.getData().isEmpty());

        assertEquals(((ActionResponse)response).getState(), ResponseState.ERROR);
        assertTrue(response.getDescription().contains(EXCEPTION_MESSAGE));
    }
}
