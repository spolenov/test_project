package com.century.test_project_spolenov.controller;

import com.century.test_project_spolenov.service.order.OrderService;
import com.century.test_project_spolenov.service.order.OrderServiceImpl;
import com.century.test_project_spolenov.service.response.Response;

public class OrderController {
    OrderService orderService = new OrderServiceImpl();

    Response getAll(){
        return orderService.getAll();
    }
}
