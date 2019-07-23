package com.century.test_project_spolenov.order;

import com.century.test_project_spolenov.model.order.OrderLine;
import com.century.test_project_spolenov.repository.order.OrderLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/spring-db-test.xml"})
class OrderLineTest {

    @Autowired
    private OrderLineRepository orderLineRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void getAllLinesTest(){
        List<OrderLine> lines = orderLineRepository.findAll();
        assertNotNull(lines);
        assertFalse(lines.isEmpty());
    }

    @Test
    void updateLineTest(){
        List<OrderLine> lines = orderLineRepository.findAll();
        OrderLine line = lines.get(0);

        line.setCount(line.getCount() + 1);
        Executable ex = () -> orderLineRepository.save(line);

        assertDoesNotThrow(ex);
    }

    @Test
    void deleteLinesTest(){
        String sql = "SELECT id FROM test.order_line WHERE order_id IN (" +
                "SELECT order_id FROM test.order_line " +
                "GROUP BY order_id " +
                "HAVING COUNT(id) > 1 " +
                ") LIMIT 1";
        List<Integer> ids = jdbcTemplate.queryForList(sql, Integer.class);

        assertFalse(ids.isEmpty(), "Must have order with line count " +
                "greater than 1 for this test.");

        for(Integer id: ids){
            orderLineRepository.deleteById(id);
        }
    }

}
