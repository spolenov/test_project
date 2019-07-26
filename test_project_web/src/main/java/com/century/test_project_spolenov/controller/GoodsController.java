package com.century.test_project_spolenov.controller;

import com.century.test_project_spolenov.service.goods.GoodsService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.century.test_project_spolenov.service.response.ActionResponse.errResponse;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@SuppressWarnings("unchecked")
@RequestMapping(value = "/goods", produces = APPLICATION_JSON_VALUE)
public class GoodsController {
    private GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService){
        this.goodsService = goodsService;
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Response getAll(){
        return goodsService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Response getById(@PathVariable("id") int id){
        return goodsService.getById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Response deleteOne(@PathVariable("id") int id){
        return goodsService.deleteOne(id);
    }

    @PutMapping(value = "/add")
    @ResponseBody
    public Response addNew(@RequestBody BaseRequest request){
        return goodsService.addNew(request);
    }

    @PostMapping(value = "/update/one")
    @ResponseBody
    public Response updateOne(@RequestBody BaseRequest request){
        return goodsService.updateOne(request);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e){
        return errResponse(String.format("Ошибка в %s", this.getClass().getSimpleName()), e);
    }
}
