package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.common.CommonService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.ActionResponse;
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

import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private CommonService<Order> commonService;

    private Order order = new Order();
    private BaseRequest<Order> request = new BaseRequest<>(new ArrayList<Order>(){{add(order);}});
    private ActionResponse response = okResponse(new ArrayList<Order>(){{add(order);}});

    @BeforeEach
    private void init(){
        when(commonService.getAll()).thenReturn(response);
        when(commonService.getById(eq(order.getId()))).thenReturn(response);
        when(commonService.addNew(eq(request))).thenReturn(okResponse());
        when(commonService.deleteOne(eq(order.getId()))).thenReturn(okResponse());
        when(commonService.updateOne(eq(request))).thenReturn(okResponse());
    }

    @Test
    void getAllTest(){
        assertEquals(orderService.getAll(), response);
    }

    @Test
    void addNewTest(){
        assertEquals(orderService.addNew(request), okResponse());
    }

    @Test
    void deleteOneTest(){
        assertEquals(orderService.deleteOne(order.getId()), okResponse());
    }

    @Test
    void updateOneTest(){
        assertEquals(orderService.updateOne(request), okResponse());
    }

    @Test
    void getByIdTest(){
        assertEquals(orderService.getById(order.getId()), response);
    }
}
