package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.response.BaseResponse;
import com.century.test_project_spolenov.service.response.Response;
import com.century.test_project_spolenov.service.response.ResponseState;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

@Slf4j
public class OrderServiceImpl implements OrderService{

    private Response getOkOrderResponse(Order order){
        return new OrderResponse(singletonList(order), ResponseState.OK);
    }
    private Response getErrorOrderResponse(Order order){
        log.info("test");
        return new OrderResponse(singletonList(order), ResponseState.ERROR);
    }

    @Override
    public Response getAll() {
        List<Order> responses = new ArrayList<>();
        responses.add(new Order(1));
        responses.add(new Order(2));

        return new OrderListResponse(responses);
    }

    private class OrderResponse extends ActionResponse<Order> {
        OrderResponse(List<Order> data, ResponseState state) {
            super(data, state);
        }

        @Override
        public String getDescription() {
            return getData().toString();
        }
    }

    private class OrderListResponse extends BaseResponse<Order> {
        OrderListResponse(List<Order> data) {
            super(data);
        }

        @Override
        public String getDescription() {
            return getData() == null? "":  getData().size() + " elements";
        }
    }
}
