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

import java.util.List;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static com.century.test_project_spolenov.service.response.ActionResponse.okResponse;
import static java.util.Collections.singletonList;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    private Response getErrorOrderResponse(Order order){
        return new OrderResponse(singletonList(order), ResponseState.ERROR);
    }

    @Override
    public Response getAll() {
        List<Order> orders;

        try{
            orders = orderRepository.findAll();
        } catch (Exception e){
            return errResponse("Failed to get all orders", e);
        }

        if(orders.isEmpty()){
            return errResponse("No orders found in database.");
        }
        return new OrderResponse(orders, ResponseState.OK);
    }

    @Override
    public Response getById(int id) {
        Order order;

        try{
            order = orderRepository.findById(id).orElse(null);
        } catch (Exception e){
            return errResponse(String.format("Failed to get order by id = %d", id), e);
        }

        if(order == null){
            return errResponse(String.format("No order found by id = %d", id));
        }

        return new OrderResponse(order, ResponseState.OK);
    }

    @Override
    public Response deleteOne(int id) {
        return null;
    }

    @Override
    public Response addNew(OrderRequest request) {
        request.assertDataHasSingleElement();

        return null;
    }

    @Override
    public Response updateOne(OrderRequest request) {
        request.assertDataHasSingleElement();
        Order order = request.getSingleElement();

        try{
            orderRepository.save(order);
        } catch (Exception e){
            return errResponse(String.format("Failed to update order (id = %d)", order.getId()), e);
        }
        return okResponse();
    }

    private class OrderResponse extends ActionResponse<Order> {
        OrderResponse(List<Order> data, ResponseState state) {
            super(data, state);
        }

        OrderResponse(Order data, ResponseState state) {
            super(singletonList(data), state);
        }

        @Override
        public String getDescription() {
            return getData().toString();
        }
    }
}
