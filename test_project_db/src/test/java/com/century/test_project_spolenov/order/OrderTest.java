package com.century.test_project_spolenov.order;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.model.order.OrderLine;
import com.century.test_project_spolenov.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
@TestMethodOrder(MethodOrderer.Random.class)
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
        List<Order> orders = orderRepository.findAll();

        for(Order order: orders){
            Order foundOrder = orderRepository.findById(order.getId()).orElse(null);
            assertNotNull(foundOrder);

            assertEquals(foundOrder.getId(), order.getId());
        }
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
    void addNewLineTest(){
        Order order = orderRepository.findAll()
                .stream()
                .findAny()
                .orElse(null);
        assertNotNull(order);

        int orderId = order.getId();
        int linesCount = order.getLines().size();
        assertTrue(linesCount > 0);

        OrderLine newLine = new OrderLine();
        newLine.setOrder(order);

        Goods newGoods = new Goods();
        newGoods.setId(1);

        newLine.setGoods(newGoods);
        newLine.setCount(1);
        order.getLines().add(newLine);

        orderRepository.save(order);
        order = orderRepository.findById(orderId).orElse(null);
        assertNotNull(order);
        assertNotNull(order.getLines());

        assertEquals(linesCount + 1, order.getLines().size());
    }

    @Test
    void updateLineTest(){
        Order order = orderRepository.findAll()
                .stream()
                .findAny()
                .orElse(null);
        assertNotNull(order);

        int orderId = order.getId();
        int linesCount = order.getLines().size();
        assertTrue(linesCount > 0);

        OrderLine currentLine = order.getLines().iterator().next();
        int countBefore = currentLine.getCount();
        int lineId = currentLine.getId();

        currentLine.setCount(countBefore + 1);
        orderRepository.save(order);

        order = orderRepository.findById(orderId).orElse(null);
        assertNotNull(order);
        assertNotNull(order.getLines());

        OrderLine foundLine = order.getLines()
                .stream().filter(l -> l.getId() == lineId)
                .findAny()
                .orElse(null);
        assertNotNull(foundLine);

        assertEquals(foundLine.getCount(), countBefore + 1);
    }

    @Test
    void toStringTest(){
        assertFalse(new Order().toString().isEmpty());
    }
}
