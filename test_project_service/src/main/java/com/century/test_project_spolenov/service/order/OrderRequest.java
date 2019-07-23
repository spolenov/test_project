package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.response.BaseRequest;

import java.util.List;

public class OrderRequest extends BaseRequest<Order> {
    public OrderRequest(List<Order> data) {
        super(data);
    }

    public OrderRequest(Order data) {
        super(data);
    }
}
