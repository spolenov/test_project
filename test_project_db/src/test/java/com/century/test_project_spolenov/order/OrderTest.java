package com.century.test_project_spolenov.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void getAllOrdersTest(){
        List<Order> totalOrders = orderRepository.findAll();
        assertNotNull(totalOrders);

        assertFalse(totalOrders.isEmpty());

        for(Order o: totalOrders){
            assertNotNull(o.getClient());
            assertThat(o.getClient().getId(), greaterThan(0));
        }

        totalOrders.forEach(r -> log.info(r.toString()));
    }

    @Test
    void getOrderByIdTest(){
        final int TEST_ID = 1;
        Order existingOrder = orderRepository.findAll().get(0);
        existingOrder.setId(TEST_ID);

        orderRepository.save(existingOrder);

        Order order = orderRepository.findById(TEST_ID).orElse(null);
        assertNotNull(order);

        assertEquals(order.getId(), TEST_ID);
    }

    @Test
    void saveExistingOrderTest(){
        List<Order> totalOrders = orderRepository.findAll();
        assertFalse(totalOrders.isEmpty());

        int sizeBefore = totalOrders.size();

        for(Order o: totalOrders){
            assertDoesNotThrow(() -> orderRepository.save(o));
        }
        assertEquals(orderRepository.findAll().size(), sizeBefore);
    }

    @Test
    void saveOrderWithConflictIdTest(){
        Order orderToSave = new Order();
        orderToSave.setId(orderRepository.findAll().iterator().next().getId());

        assertThrows(DataIntegrityViolationException.class, () -> orderRepository.save(orderToSave));
    }


    @Test
    void deleteOrderTest(){
        Order order = orderRepository.findAll()
                .stream()
                .findAny()
                .orElse(null);
        assertNotNull(order);

        int id =  order.getId();
        Executable ex = () ->  orderRepository.deleteById(id);
        assertDoesNotThrow(ex);
    }

    @Test
    void testToString(){
        assertFalse(new Order().toString().isEmpty());
    }
}
