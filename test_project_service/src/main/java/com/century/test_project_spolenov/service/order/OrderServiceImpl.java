package com.century.test_project_spolenov.service.order;

import com.century.test_project_spolenov.model.order.Order;
import com.century.test_project_spolenov.repository.order.OrderRepository;
import com.century.test_project_spolenov.service.common.CommonService;
import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.ActionResponse;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@EqualsAndHashCode
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    private CommonService<Order> commonService;

    @Autowired
    public OrderServiceImpl(OrderRepository goodsRepository,
                            CommonService<Order> commonService){
        this.orderRepository = goodsRepository;
        this.commonService = commonService;
    }

    @PostConstruct
    public void initServiceRepository(){
        this.commonService.setRepository(orderRepository);
    }

    @Override
    public ActionResponse getAll() {
        return commonService.getAll();
    }

    @Override
    public ActionResponse getById(int id) {
        return commonService.getById(id);
    }

    @Override
    public ActionResponse deleteOne(int id) {
        return commonService.deleteOne(id);
    }

    @Override
    public ActionResponse addNew(Request<Order> request) {
        return commonService.addNew(request);
    }

    @Override
    public ActionResponse updateOne(Request<Order> request) {
        return commonService.updateOne(request);
    }
}
