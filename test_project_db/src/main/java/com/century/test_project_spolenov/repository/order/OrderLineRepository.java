package com.century.test_project_spolenov.repository.order;

import com.century.test_project_spolenov.model.order.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
