package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.order.OrderRepository;
import com.century.test_project_spolenov.service.response.ActionResponse;
import com.century.test_project_spolenov.service.response.BaseResponse;
import com.century.test_project_spolenov.service.response.Response;
import com.century.test_project_spolenov.service.response.ResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

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

        return new OrderListResponse(orderRepository.findById(1).orElse(null));
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

        OrderListResponse(Order data) {
            super(data);
        }

        @Override
        public String getDescription() {
            return getData() == null? "":  getData().size() + " elements";
        }
    }
}
