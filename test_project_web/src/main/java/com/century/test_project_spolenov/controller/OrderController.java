package com.century.test_project_spolenov.controller;

import com.century.test_project_spolenov.service.order.OrderService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.BaseResponse;
import com.century.test_project_spolenov.service.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@SuppressWarnings("unchecked")
@RequestMapping(value = "/order", produces = APPLICATION_JSON_VALUE)
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Response getAll(){
        return orderService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Response getById(@PathVariable("id") int id){
        return orderService.getById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Response deleteOne(@PathVariable("id") int id){
        return orderService.deleteOne(id);
    }

    @PutMapping(value = "/add")
    @ResponseBody
    public Response addNew(@RequestBody BaseRequest request){
        return orderService.addNew(request);
    }

    @PostMapping(value = "/update/one")
    @ResponseBody
    public Response updateOne(@RequestBody BaseRequest request){
        return orderService.updateOne(request);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e){
        return errResponse(String.format("Ошибка в %s", this.getClass().getSimpleName()), e);
    }
}
