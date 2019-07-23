package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.order.OrderRepository;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.response.Response;
import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;

    private Order order;
    private List<Order> orders = new ArrayList<>();
    private OrderRequest orderRequest;

    @BeforeEach
    private void initBeforeEach(){
        final int orderId = 5;

        order = new Order();
        order.setId(orderId);
        order.setAddress("test_address");
        order.setOrderDate(new Date());
        orderRequest = new OrderRequest(order);

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
    void updateOneTest(){
        Response response = orderService.updateOne(orderRequest);
        assertEquals(response, ActionResponse.okResponse());

        verify(orderRepository, times(1))
                .save(eq(orderRequest.getSingleElement()));
    }

    @Test
    void getByIdTest(){
        Response response = orderService.getById(order.getId());
        assertNotNull(response.getData());

        assertEquals(response.getData().get(0), order);
        verify(orderRepository, times(1))
                .findById(eq(order.getId()));
    }
}
