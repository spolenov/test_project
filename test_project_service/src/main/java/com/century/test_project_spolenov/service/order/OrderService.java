package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.DocType;
import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.common.BaseService;
import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.Response;

public interface OrderService extends BaseService {
    Response getAll();
    Response getById(int id);
    Response deleteOne(int id);

    Response addNew(Request<Order> request);
    Response updateOne(Request<Order> request);

    default DocType getEligibleDocType(){
        return DocType.ORDER;
    }
}
