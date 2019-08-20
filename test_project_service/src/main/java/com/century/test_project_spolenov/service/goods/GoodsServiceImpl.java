package com.century.test_project_spolenov.service.goods;

import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.repository.goods.GoodsRepository;
import com.century.test_project_spolenov.service.common.CommonService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    private GoodsRepository goodsRepository;

    private CommonService<Goods> commonService;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository,
                            CommonService<Goods> commonService){
        this.goodsRepository = goodsRepository;
        this.commonService = commonService;
    }

    @PostConstruct
    public void initServiceRepository(){
        commonService.setRepository(goodsRepository);
    }

    @Override
    public Response getAll() {
        return commonService.getAll();
    }

    @Override
    public Response getById(int id) {
        return commonService.getById(id);
    }

    @Override
    public Response deleteOne(int id) {
        return commonService.deleteOne(id);
    }

    @Override
    public Response addNew(BaseRequest<Goods> request) {
        return commonService.addNew(request);
    }

    @Override
    public Response updateOne(BaseRequest<Goods> request) {
        return commonService.updateOne(request);
    }
}
