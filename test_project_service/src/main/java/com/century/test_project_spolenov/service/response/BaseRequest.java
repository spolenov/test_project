package com.century.test_project_spolenov.service.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseRequest<T> implements Request<T>{
    private List<T> data;

    public List<T> getData(){
        return data;
    }

    public BaseRequest(List<T> data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }
        this.data = data;
    }

    public BaseRequest(T data){
        if(data == null){
            this.data = new ArrayList<>();
            return;
        }

        this.data = Collections.singletonList(data);
    }

    public T getSingleElement(){
        return data.get(0);
    }
}
