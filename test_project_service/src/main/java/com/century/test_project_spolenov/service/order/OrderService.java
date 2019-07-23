package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.service.response.Response;

public interface OrderService {
    Response getAll();
    Response getById(int id);
    Response deleteOne(int id);

    Response addNew(OrderRequest request);
    Response updateOne(OrderRequest request);
}
