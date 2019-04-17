package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.response.BaseResponse;
import com.century.test_project_spolenov.service.response.Response;
import com.century.test_project_spolenov.service.response.ResponseState;

import java.util.ArrayList;
import java.util.List;


public class OrderServiceImpl implements OrderService{

    private Response getOkOrderResponse(Order order){
        return new OrderResponse(order, ResponseState.OK);
    }
    private Response getErrorOrderResponse(Order order){
        return new OrderResponse(order, ResponseState.ERROR);
    }

    @Override
    public Response getAll() {
        List<Order> responses = new ArrayList<>();
        responses.add(new Order(1));
        responses.add(new Order(2));

        return new OrderListResponse(responses);
    }

    private class OrderResponse extends ActionResponse<Order> {
        OrderResponse(Order entity, ResponseState state) {
            super(entity, state);
        }

        @Override
        public String getDataInfo() {
            return getData().toString();
        }
    }

    private class OrderListResponse extends BaseResponse<List<Order>> {
        OrderListResponse(List<Order> data) {
            super(data);
        }

        @Override
        public String getDataInfo() {
            return getData() == null? "":  getData().size() + " elements";
        }
    }
}
