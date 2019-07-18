package com.century.test_project_spolenov.repository.order;

import com.century.test_project_spolenov.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
