package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.util.MockitoExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = {"classpath:/spring/test-project-service.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
class OrderRequestValidationTest {
    @Test
    void verifyDataEmptinessOnOrderRequestTest(){
        BaseRequest orderRequest = new BaseRequest<Order>();
        assertThrows(IllegalArgumentException.class, orderRequest::verifyDataNotEmpty);

        orderRequest.setData(new ArrayList<>());
        assertThrows(IllegalArgumentException.class, orderRequest::verifyDataNotEmpty);

        orderRequest.getData().add(new Order());
        assertDoesNotThrow(orderRequest::verifyDataNotEmpty);
    }

}
