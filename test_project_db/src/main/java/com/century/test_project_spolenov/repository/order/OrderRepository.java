package com.century.test_project_spolenov.repository.order;

import com.century.test_project_spolenov.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    void deleteByid(@Param("id") int id);
}
