package com.century.test_project_spolenov.service.goods;

import com.century.test_project_spolenov.model.DocType;
import com.century.test_project_spolenov.model.goods.Goods;
import com.century.test_project_spolenov.service.common.BaseService;
import com.century.test_project_spolenov.service.request.BaseRequest;
import com.century.test_project_spolenov.service.response.Response;

public interface GoodsService extends BaseService {
    Response getAll();
    Response getById(int id);
    Response deleteOne(int id);

    Response addNew(BaseRequest<Goods> request);
    Response updateOne(BaseRequest<Goods> request);

    default DocType getEligibleDocType(){
        return DocType.GOODS;
    }
}
