package com.century.test_project_spolenov.service.common;

import com.century.test_project_spolenov.service.request.Request;
import com.century.test_project_spolenov.service.response.ActionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonService<T> extends BaseService {
    ActionResponse getAll();
    ActionResponse getById(int id);
    ActionResponse deleteOne(int id);

    ActionResponse addNew(Request<T> request);
    ActionResponse updateOne(Request<T> request);

    void setRepository(JpaRepository<T, Integer> repository);
}
